using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.ApplicationModel.DataTransfer;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Alasita.App.Views
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class Main : Page
    {
        
        public Main()
        {
            this.InitializeComponent();
            //Share JV
            RegisterForShare();
        }


        //Share JV [
        private string text_to_share = "";
        private void RegisterForShare()
        {
            DataTransferManager dataTransferManager = DataTransferManager.GetForCurrentView();
            dataTransferManager.DataRequested += new TypedEventHandler<DataTransferManager, DataRequestedEventArgs>(this.ShareTextHandler);
        }
        private void ShareTextHandler(DataTransferManager sender, DataRequestedEventArgs e)
        {
            DataRequest request = e.Request;
            request.Data.Properties.Title = "Share";
            request.Data.Properties.Description = "Que estas pensando";
            request.Data.SetText(text_to_share + Environment.NewLine+Environment.NewLine+ "Share by Alasitas 2015");
        }
        private void btn_share_Click(object sender, RoutedEventArgs e)
        {
            text_to_share = txt_share.Text;
            DataTransferManager.ShowShareUI();
        }
        //] End Share JV
    }
}
