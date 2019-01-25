package com.charlie.gwtmodel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwtTabPanel extends Composite {
	private static GwtTabPanelUiBinder uiBinder = GWT.create(GwtTabPanelUiBinder.class);

	/*
	 * @UiTemplate is not mandatory but allows multiple XML templates
	 * to be used for the same widget. 
	 * Default file loaded will be <class-name>.ui.xml
	 */

	@UiTemplate("GwtTabPanel.ui.xml")
	interface GwtTabPanelUiBinder extends UiBinder<Widget, GwtTabPanel> {
	}
	
	@UiField
	GwtModelFirst gwtPanelModel;
	
	@UiField
	GwtCompetition gwtPanelCompetition;
	
	@UiField
	GwtSearchPanel gwtPanelSearch;

	@UiField
	TabPanel gwtTabPanel;

    public GwtTabPanel() {
        initWidget(uiBinder.createAndBindUi(this));
//        
//        ClientCache.getInstance().setMainPanel(this);
//        deptStructTab.addShownHandler(new TabShownHandler() {
// 			@Override
// 			public void onShown(TabShownEvent event) {
// 				testDeptStructurePanel.departmentName4.setFocus(true);																					
// 			}
// 		});
//        userGuideTab.addShownHandler(new TabShownHandler() {
// 			@Override
// 			public void onShown(TabShownEvent event) {
// 				tutorialsPanel.tutorialIntroductionButton2.setFocus(true);																					
// 			}
// 		});
//        feedbackTab.addShownHandler(new TabShownHandler() {
// 			@Override
// 			public void onShown(TabShownEvent event) {
// 				feedback.extension.setFocus(true);																					
// 			}
// 		});
//        hrmisTab.addShownHandler(new TabShownHandler() {
// 			@Override
// 			public void onShown(TabShownEvent event) {
// 				updateMyPhoneNumberPanel.selfServiceButton.setFocus(true);																					
// 			}
// 		});
//        nameSearchTab.addShownHandler(new TabShownHandler() {
// 			@Override
// 			public void onShown(TabShownEvent event) {
// 				nameSearchPanel.criteriaPanel.firstNameTextBox.setFocus(true);																					
// 			}
// 		});
        gwtTabPanel.selectTab(0);
        gwtTabPanel.setVisible(true);
        
    }
    

}
