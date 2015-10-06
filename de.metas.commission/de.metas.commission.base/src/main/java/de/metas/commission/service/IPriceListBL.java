package de.metas.commission.service;

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


import org.adempiere.util.ISingletonService;
import org.compiere.model.I_M_PriceList_Version;

import de.metas.commission.interfaces.I_M_DiscountSchemaLine;

public interface IPriceListBL extends ISingletonService
{

	String updateCommissionPoints(I_M_PriceList_Version plv, I_M_DiscountSchemaLine dsl, int adPinstanceId, String trxName);

	void updateSalcePriceCommissionPoints(I_M_PriceList_Version plv, I_M_DiscountSchemaLine dsl, int adPinstanceId, String trxName);

}
