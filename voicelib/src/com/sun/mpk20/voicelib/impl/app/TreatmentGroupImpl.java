/*
 * Copyright 2007 Sun Microsystems, Inc.
 *
 * This file is part of jVoiceBridge.
 *
 * jVoiceBridge is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License version 2 as 
 * published by the Free Software Foundation and distributed hereunder 
 * to you.
 *
 * jVoiceBridge is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Sun designates this particular file as subject to the "Classpath"
 * exception as provided by Sun in the License file that accompanied this 
 * code. 
 */

package com.sun.mpk20.voicelib.impl.app;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.NameNotBoundException;

import com.sun.mpk20.voicelib.app.TreatmentGroup;
import com.sun.mpk20.voicelib.app.Treatment;
import com.sun.mpk20.voicelib.app.Util;
import com.sun.mpk20.voicelib.app.VoiceManager;

import com.sun.voip.client.connector.CallStatus;
import com.sun.voip.client.connector.CallStatusListener;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;

import java.util.logging.Logger;

import java.util.concurrent.ConcurrentHashMap;

public class TreatmentGroupImpl implements TreatmentGroup, CallStatusListener, Serializable {

    private static final Logger logger =
        Logger.getLogger(PlayerImpl.class.getName());

    private String id;

    private ConcurrentHashMap<String, Treatment> treatments = new ConcurrentHashMap();

    private int numberTreatmentsDone;

    public TreatmentGroupImpl(String id) {
	this.id = Util.generateUniqueId(id);
    }

    public String getId() {
	return id;
    }

    public void addTreatment(Treatment treatment) {
	String callId = treatment.getCall().getId();

	treatments.put(callId, treatment);

	DataManager dm = AppContext.getDataManager();

        WarmStartTreatmentGroups warmStartTreatmentGroups;

        try {
            warmStartTreatmentGroups = (WarmStartTreatmentGroups) dm.getBinding(
                WarmStartInfo.DS_WARM_START_TREATMENTGROUPS);
        } catch (NameNotBoundException e) {
            try {
                warmStartTreatmentGroups = new WarmStartTreatmentGroups();
                dm.setBinding(WarmStartInfo.DS_WARM_START_TREATMENTGROUPS, warmStartTreatmentGroups);
            }  catch (RuntimeException re) {
                logger.warning("failed to bind pending map " + re.getMessage());
                throw re;
            }
        }

        warmStartTreatmentGroups.put(id, treatments);

	AppContext.getManager(VoiceManager.class).addCallStatusListener(this, callId);
	restartTreatments();
    }

    public void removeTreatment(Treatment treatment) {
	String callId = treatment.getCall().getId();

	AppContext.getManager(VoiceManager.class).removeCallStatusListener(this, callId);
	treatments.remove(callId);

	DataManager dm = AppContext.getDataManager();

        WarmStartTreatmentGroups warmStartTreatmentGroups;

        try {
            warmStartTreatmentGroups = (WarmStartTreatmentGroups) dm.getBinding(
                WarmStartInfo.DS_WARM_START_TREATMENTGROUPS);
        } catch (NameNotBoundException e) {
            try {
                warmStartTreatmentGroups = new WarmStartTreatmentGroups();
                dm.setBinding(WarmStartInfo.DS_WARM_START_TREATMENTGROUPS, warmStartTreatmentGroups);
            }  catch (RuntimeException re) {
                logger.warning("failed to bind pending map " + re.getMessage());
                throw re;
            }
        }

        warmStartTreatmentGroups.remove(id, treatments);

	restartTreatments();
    }

    public ConcurrentHashMap<String, Treatment> getTreatments() {
	return treatments;
    }

    /*
     * Restart treatments in the group if there's more than one call
     */
    private void restartTreatments() {
	logger.fine("Restarting input treatments for " + id);

	Collection<Treatment> c = treatments.values();

	Iterator<Treatment> it = c.iterator();

	VoiceManager vm = AppContext.getManager(VoiceManager.class);

	while (it.hasNext()) {
	    Treatment treatment = it.next();

	    String callId = treatment.getCall().getId();

	    try {
		logger.fine("Restarting input treatment " + treatment);
	        vm.getBackingManager().restartInputTreatment(callId);
	    } catch (IOException e) {
	        logger.warning("Unable to restart treatment for " + callId
		    + " " + e.getMessage());
	    }
	}
    }

    public void callStatusChanged(CallStatus status) {
	int code = status.getCode();

	String callId = status.getCallId();

	if (callId == null) {
	    return;
	}

	Treatment treatment = treatments.get(callId);

	switch (code) {
        case CallStatus.ESTABLISHED:
        case CallStatus.MIGRATED:
            logger.fine("callEstablished: " + callId);
	    restartTreatments();
            break;

	case CallStatus.TREATMENTDONE:
	    logger.finer("Treatment done: " + status);

	    numberTreatmentsDone++;

	    if (numberTreatmentsDone == treatments.size()) {
		numberTreatmentsDone = 0;
	    }

	    restartTreatments();
	    break;

        case CallStatus.ENDED:
	    logger.info(status.toString());
	    removeTreatment(treatment);
	    break;

	case CallStatus.BRIDGE_OFFLINE:
	    logger.info("Bridge offline: " + status);
	    
	    /*
	     * Treatments are automatically restarted here.
	     * Clients are notified that the bridge went down
	     * and they notify the softphone which then reconnects.
	     */
	    restartTreatments();
	    break;
        }
    }

    public String toString() {
	return id;
    }
    
}