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
using Windows.UI.Xaml.Media.Animation;
// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace Alasita.App.Views
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    /// 
    
    public sealed partial class MapView : Page
    {
        Flyout fly = new Flyout();
        public MapView()
        {
            this.InitializeComponent();
        }
        private void SectorAppBarButton_Click(object sender, RoutedEventArgs e)
        {
            string key = ((AppBarButton)sender).Name;
            this.PopupSector.IsOpen = true;
            Sector s = new Sector();
            foreach (var item in DataProvider.StaticCarnival.CarnivalSectors)
            {
                if (item.SectorKey == key)
                { s = item; break; }
            }
            this.TexBlockSectorKey.DataContext = "Sector " + key;
            this.TextBlockSectorName.DataContext = s.SectorName;
            this.ListViewAsociaciones.ItemsSource = s.SectorAssociations;

           //StartAnimationElipse(sender,e);
        }


        private void StartAnimationElipse(DoubleAnimationUsingKeyFrames animation,string key)
        {
                
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

        private void ImageMap_Tapped(object sender, TappedRoutedEventArgs e)
        {
            StopAllAnims();
        }

        private async void SearchProduct_QuerySubmitted(SearchBox sender, SearchBoxQuerySubmittedEventArgs args)
        {
            List<Sector> obtain = Utilities.SearchProduct(sender.QueryText.ToLower());
            if (obtain.Count>0)
            {
                StopAllAnims();
                AnimateSectors(obtain);
                TextBlock textQuery = new TextBlock();
                textQuery.Text = "AQUI ENCONTRARAS: " + sender.QueryText;
                fly.Content = textQuery;

            }
            else
            {
                MessageDialog mes = new MessageDialog("No se encontraron resultados de tu busqueda, revisa tu ortografia","Sin resultados");
                await mes.ShowAsync();
            }
        }

        private void StopAllAnims() {
            ElipseAnimA.Stop();
            ElipseAnimB.Stop();
            ElipseAnimC.Stop();
            ElipseAnimD.Stop();
            ElipseAnimE.Stop();
            ElipseAnimF.Stop();
            ElipseAnimG.Stop();
            ElipseAnimH.Stop();
            ElipseAnimI.Stop();
            ElipseAnimN.Stop();
            ElipseAnimR.Stop();
            ElipseAnimK.Stop();

        }

        private void AnimateSectors(List<Sector> obtain)
        {
            
            foreach (var item in obtain)
            {
                switch (item.SectorKey)
                {
                    case "A": ElipseAnimA.Begin(); fly.ShowAt(EllipseA); break;
                    case "B": ElipseAnimB.Begin(); fly.ShowAt(EllipseB); break;
                    case "C": ElipseAnimC.Begin(); fly.ShowAt(EllipseC); break;
                    case "D": ElipseAnimD.Begin(); fly.ShowAt(EllipseD); break;
                    case "E": ElipseAnimE.Begin(); fly.ShowAt(EllipseE); break;
                    case "F": ElipseAnimF.Begin(); fly.ShowAt(EllipseF); break;
                    case "G": ElipseAnimG.Begin(); fly.ShowAt(EllipseG); break;
                    case "H": ElipseAnimH.Begin(); fly.ShowAt(EllipseH); break;
                    case "I": ElipseAnimI.Begin(); fly.ShowAt(EllipseI); break;
                    case "N": ElipseAnimN.Begin(); fly.ShowAt(EllipseN); break;
                    case "R": ElipseAnimR.Begin(); fly.ShowAt(EllipseR); break;
                    case "K": ElipseAnimK.Begin(); fly.ShowAt(EllipseK); break;
                }
            }
            
        }

        private void GridSector_Tapped(object sender, TappedRoutedEventArgs e)
        {
            Sector s = new Sector();
            s = ((((Grid)sender).DataContext) as Sector);
            this.TexBlockSectorKey.DataContext = "Sector " +s.SectorKey;
            this.TextBlockSectorName.DataContext = s.SectorName;
            this.ListViewAsociaciones.ItemsSource = s.SectorAssociations;
            this.PopupSector.IsOpen = true;
        }
    }
}
