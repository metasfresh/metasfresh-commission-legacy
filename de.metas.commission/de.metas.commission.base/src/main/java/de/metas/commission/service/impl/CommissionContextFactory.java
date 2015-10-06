package de.metas.commission.service.impl;

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

import org.compiere.model.I_M_Product;

import de.metas.commission.custom.type.ICommissionType;
import de.metas.commission.model.I_C_AdvComSystem;
import de.metas.commission.model.I_C_Sponsor;
import de.metas.commission.service.ICommissionContext;
import de.metas.commission.service.ICommissionContextFactory;

public class CommissionContextFactory implements ICommissionContextFactory
{

	// @Override
	// public ICommissionContext create(I_C_Sponsor sponsor, Timestamp date, ICommissionType commissionType)
	// {
	// // return new CommissionContext(sponsor, commissionType, product, date);
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public ICommissionContext create(I_C_Sponsor sponsor, Timestamp date, ICommissionType commissionType, I_C_AdvComSystem comSystem)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public ICommissionContext create(final I_C_Sponsor sponsor, final Timestamp date, final ICommissionType commissionType, final I_M_Product product)
	{
		return new CommissionContext(sponsor, commissionType, product, date);
	}

	@Override
	public ICommissionContext create(final I_C_Sponsor sponsor, final Timestamp date, final ICommissionType commissionType, final I_C_AdvComSystem comSystem, final I_M_Product product)
	{
		return new CommissionContext(sponsor, commissionType, comSystem, product, date);
	}

}
