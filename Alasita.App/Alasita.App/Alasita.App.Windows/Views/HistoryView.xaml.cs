using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Alasita.App.Core.Util;
using Alasita.App.Core.Models;
// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Alasita.App.Views
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class HistoryView : Page
    {
        public HistoryView()
        {
            this.InitializeComponent();
        }

        private void BackBotton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Main));
        }
        protected async override void OnNavigatedTo(NavigationEventArgs e)
        {
            
            this.GridHistory.DataContext = await Utilities.LoadAnHistory(e.Parameter+"");
        }
    }
}
