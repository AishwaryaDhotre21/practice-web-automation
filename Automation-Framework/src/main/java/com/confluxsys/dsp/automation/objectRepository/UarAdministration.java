package com.confluxsys.dsp.automation.objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public interface UarAdministration
{
    String uarDashboardTitle= "//*[contains(text(),'Certification Dashboard')]";
    String createButton= "//button[contains(text(),'Create')]";
    String    campaignNameField = "//app-create-campaign//div[@class='card-body']/form//div/input[@id='Name']";
    String campaignDescriptionField = "//app-create-campaign//div[@class='card-body']/form//div/input[@id='description']";
    String searchButton = "//span[@class='form-group']";
    String li_programName = "//div[@class='suggestions-container is-visible']/ul/li/div/a";
    String submitButton= "//div[@class='col-md-2 form-group']";

    String confirmDataAlert = "//p[contains(text(),'Are you sure you want to create new Campaign')]";
    String alertConfirmButton = "//button[normalize-space()='Confirm']";
    String confirmationBox="//*[contains(text(),'Confirmation')]";

    String actionButton="//span[contains(text(),'Action')]";

    String okButton="//button[contains(text(),' OK ')]";
    String li_Campaign_name    = "//table[@id='campaignManageId']/tbody//td[@class='text-center']/following-sibling::td[@class='w-25P'][1]";


    String li_program_name= "//table[@id='campaignManageId']/tbody//td[@class='text-center']/following-sibling::td[@class='w-25P'][2]";

    String li_dates= "//table[@id='campaignManageId']/tbody//td[@class='text-center']/following-sibling::td[3]";

    String descriptionError= "//div[contains(text(),' Campaign Description is required.')]";

    String campaignNameError = "//div[contains(text(),' Campaign name is required')]";

    String duplicateCampaignName = "//div[@id='campaignManage']/table[@id='campaignManageId']/tbody/tr/td[2]";

    String tableRowPresenceCheck = "//div[@id='campaignManage']/table[@id='campaignManageId']/tbody/tr[1]";

    String DuplicateCampaignErrorMsg       = "//*[contains(text(),'Duplicate')]";

    String duplicateCampaignAlertOkButton = "//button[contains(text(),'OK')]";

}