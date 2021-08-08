package datatouch.uikit.core.fragmentsignaling.exceptions

import datatouch.uikit.core.fragmentsignaling.base.SigSlotId

class SlotNoActionsException(slotId: SigSlotId) : Exception("Slot has no actions slotId=$slotId")