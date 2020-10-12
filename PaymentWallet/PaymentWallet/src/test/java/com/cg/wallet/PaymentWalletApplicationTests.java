package com.cg.wallet;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.wallet.dto.AddMoneyDto;
import com.cg.wallet.dto.DeleteAccountDto;
import com.cg.wallet.dto.UserDto;
import com.cg.wallet.exception.WalletAccountDoNotExistsException;
import com.cg.wallet.exception.WalletAccountExistsException;
import com.cg.wallet.service.AddWalletServiceImpl;

@SpringBootTest
class PaymentWalletApplicationTests {
	
	@Autowired
	private AddWalletServiceImpl service;
	
	/**********************************************************************************************
	* Method         :  testAddWallet1
	* Description    :  To test whether new wallet account is created successfully or not
	                    when user create account with the phoneNo that do not exists in database .
	* Created By     :  Parteek Goel
	* Created Date   :  8-OCT-2020
	************************************************************************************************/
	@Test
	public void testAddWallet1() throws WalletAccountExistsException 
	{
		UserDto user = new UserDto();
		
		user.setPhoneNo("1111");
		user.setBalance(500);
		user.setUserName("ankit");
		user.setPassword("abcde");
		
		String result= service.addNewWalletAccount(user);
		Assertions.assertEquals("Wallet account created successfully",result);
	}
	
	
	/**************************************************************************************
	* Method         :  testAddWallet2
	* Description    :  To test whether it throws WalletAccountExistsException successfully 
	                    when user give the phoneNo that exists in database.               
	* Created By     :  Parteek Goel
	* Created Date   :  8-OCT-2020
	****************************************************************************************/
	@Test
	public void testAddWallet2() throws WalletAccountExistsException 
	{
		UserDto user = new UserDto();
		
		user.setPhoneNo("7417361212");
		user.setBalance(5000);
		user.setUserName("kartik gupta");
		user.setPassword("forever21");

		assertThrows(WalletAccountExistsException.class, ()->service.addNewWalletAccount(user));
	}
	
	/**************************************************************************************
	* Method         :  testAddMoney1
	* Description    :  To test whether money added to user wallet sucessfully or not
	                    when user give the phoneNo that exists in database .
	* Created By     :  Parteek Goel
	* Created Date   :  8-OCT-2020
	****************************************************************************************/
	@Test
	public void testAddMoney1() throws WalletAccountDoNotExistsException 
	{
		AddMoneyDto addmoney = new AddMoneyDto();
		
		addmoney.setPhoneNo("7417361212");
	    addmoney.setAmount(200);
				
		String result= service.addMoney(addmoney);
		Assertions.assertEquals("Money Added to wallet",result);
	}
	/**************************************************************************************************
	* Method         :  testAddMoney2
	* Description    :  To test whether it throws WalletAccountDoNotExistsException sucessfully or not
	                    when user give the phoneNo that do not exists in database .
	* Created By     :  Parteek Goel
	* Created Date   :  8-OCT-2020
	****************************************************************************************************/
    @Test
	public void testAddMoney2() throws WalletAccountDoNotExistsException 
	{
      
    	AddMoneyDto addmoney = new AddMoneyDto();
		
		addmoney.setPhoneNo("0");
	    addmoney.setAmount(200);
	    
		assertThrows(WalletAccountDoNotExistsException.class, ()->service.addMoney(addmoney));
	} 
    /**************************************************************************************
	* Method         :  deleteWalletAccount1
	* Description    :  To test whether user wallet account sucessfully deleted or not
	                    when user give the phoneNo that exists in database .
	* Created By     :  Parteek Goel
	* Created Date   :  12-OCT-2020
	****************************************************************************************/
    
    @Test
	public void deleteWalletAccount1() throws WalletAccountDoNotExistsException 
	{
		DeleteAccountDto deleteaccount = new DeleteAccountDto();
		
		deleteaccount.setPhoneNo("9897553074");
				
		String result= service.deleteWalletAccount(deleteaccount);
		Assertions.assertEquals("Wallet Account Deleted Succesfully",result);
	}
    
    
    /**************************************************************************************************
	* Method         :  deleteWalletAccount2
	* Description    :  To test whether it throws WalletAccountDoNotExistsException sucessfully or not
	                    when user give the phoneNo that do not exists in database .
	* Created By     :  Parteek Goel
	* Created Date   :  12-OCT-2020
	****************************************************************************************************/
    @Test
	public void deleteWalletAccount2() throws WalletAccountDoNotExistsException 
	{
      
        DeleteAccountDto deleteaccount = new DeleteAccountDto();
		
		deleteaccount.setPhoneNo("00000000");
	    
		assertThrows(WalletAccountDoNotExistsException.class, ()->service.deleteWalletAccount(deleteaccount));
	} 

	
}
