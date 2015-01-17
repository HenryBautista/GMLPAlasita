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
using Alasita.App.Core.Models;
using Alasita.App.Core.Data;
using Windows.UI.Popups;
using Alasita.App.Core.Util;
// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Alasita.App.Views
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MapView : Page
    {
        public MapView()
        {
            this.InitializeComponent();
        }
        private void SectorAppBarButton_Click(object sender, RoutedEventArgs e)
        {
            this.PopupSector.IsOpen = true;
            this.TexBlockSectorName.Text = this.TexBlockSectorName.Text + (sender as AppBarButton).Label;
        }

        private void ClosePopup_Click(object sender, RoutedEventArgs e)
        {
            this.PopupSector.IsOpen = false;
        }
        private void Grid_Tapped(object sender, TappedRoutedEventArgs e)
        {
            Association asso = new Association();
            asso.AssociationName = "Hola Se supone que esto funcione asi..";
            //Data context Association 
            this.GridDataAssociation.DataContext = asso;

        }
        protected async override void OnNavigatedTo(NavigationEventArgs e)
        {
            LoadData();
        }

        private async void LoadData()
        {
            try
            {
                if (DataProvider.StaticCarnival == null)
                    DataProvider.StaticCarnival = await Utilities.LoadTheLastHope();
                this.ListViewSectores.ItemsSource = DataProvider.StaticCarnival.CarnivalSectors;

            }
            catch (Exception e)
            {

                MessageDialog ms = new MessageDialog(e.ToString(), "Error");
                ms.ShowAsync();
            }
        }

        private void AppBarButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(HistoryView));
        }

        private void BackButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Main));
        }
    }
}
