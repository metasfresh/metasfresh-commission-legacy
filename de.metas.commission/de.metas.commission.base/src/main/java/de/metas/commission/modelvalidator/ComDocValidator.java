package de.metas.commission.modelvalidator;

/*
 * #%L
 * de.metas.commission.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.sql.Timestamp;

import org.adempiere.util.Services;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;

import de.metas.commission.model.I_C_AdvComDoc;
import de.metas.commission.model.MCAdvComDoc;
import de.metas.commission.model.X_C_AdvComDoc;
import de.metas.commission.service.ICommissionFactCandBL;
import de.metas.i18n.Msg;

public class ComDocValidator implements ModelValidator
{
	private static final String MSG_ADV_COM_DATE_FACT_BEFORE_DATE_DOC = "AdvCom_DateFact_Before_DateDoc";
	private int ad_Client_ID = -1;

	@Override
	public String docValidate(final PO po, final int timing)
	{
		return null;
	}

	@Override
	public int getAD_Client_ID()
	{
		return ad_Client_ID;
	}

	@Override
	public final void initialize(final ModelValidationEngine engine, final MClient client)
	{
		if (client != null)
		{
			ad_Client_ID = client.getAD_Client_ID();
		}
		engine.addModelChange(I_C_AdvComDoc.Table_Name, this);
	}

	@Override
	public String login(final int AD_Org_ID, final int AD_Role_ID, final int AD_User_ID)
	{
		return null;
	}

	@Override
	public String modelChange(final PO po, final int type) throws Exception
	{
		if (type != ModelValidator.TYPE_BEFORE_CHANGE && type != ModelValidator.TYPE_BEFORE_NEW)
		{
			return null;
		}

		final MCAdvComDoc comDoc = (MCAdvComDoc)po;

		if (po.is_ValueChanged(I_C_AdvComDoc.COLUMNNAME_DateFact_Override))
		{
			if (comDoc.getDateFact_Override() != null)
			{
				final PO referencedDoc = comDoc.retrievePO();
				final Timestamp referencedDocDate = Services.get(ICommissionFactCandBL.class).retrieveDateDocOfPO(referencedDoc);

				if (referencedDocDate.after(comDoc.getDateFact_Override()))
				{
					return Msg.translate(po.getCtx(), ComDocValidator.MSG_ADV_COM_DATE_FACT_BEFORE_DATE_DOC);
				}
			}
		}

		if (po.is_ValueChanged(I_C_AdvComDoc.COLUMNNAME_DocStatus))
		{
			if (X_C_AdvComDoc.DOCSTATUS_Fertiggestellt.equals(comDoc.getDocStatus()))
			{
				comDoc.setIsProcessedByComSystem(false);
			}
		}
		return null;
	}
}
