package VASL.build.module.fullrules.Game;

class CampaignC extends Gamecontrol {
    // holds routines that are used at the campaign level or in scenarios
    // always inherited by scenario but can exist without scenarios

    // Constructors
    public CampaignC() {
        // called by Mainform.NewCampaign, Mainform.OpenCampaign
        // load parent
        super();
    }

    /*Friend Sub NewCampaignSetup()
            ' called by Gameform.NewCampaign
                    ' create campaign
                    'GameForm.TabCCamp.SelectTab(GameForm.TabPage2)
                    'GameForm.TabCCamp.SelectedTab.Text = "Initial Scenario"
                    'GameForm.ScenarioComboSetup()
                    'GameForm.TabCCamp.SelectTab(GameForm.TabPage1)
                    'GameForm.TabCCamp.SelectedTab.Text = "New Campaign"
                    'GameForm.TabCCamp.Visible = True
    End Sub
    Friend Sub OpenCampaign(ByVal Formloadparam As Integer)
            ' called by Gameform.OpenCampaign
                    ' open existing campaign

                    'REDO COMPLETELY AUG 14 - NOT PROGRAMMED AT ALL

    End Sub
    Friend Sub SaveCampaign(ByVal newCamp As Boolean)

    End Sub
#End Region*/
}
