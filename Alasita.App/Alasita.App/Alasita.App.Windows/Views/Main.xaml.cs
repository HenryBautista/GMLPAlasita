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
using Alasita.App.Core.Models;
using Alasita.App.Core.Data;
using Alasita.App.Core.Util;
using Windows.UI.Popups;
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
            SizeChanged += Main_SizeChanged;
        }

        void Main_SizeChanged(object sender, SizeChangedEventArgs e)
        {
            if (e.NewSize.Width<=500)
            {
                this.TextBlockCronograma.Visibility = Visibility.Collapsed;
                this.TextBlockWebSite.Visibility = Visibility.Collapsed;
            }
            else
            {
                this.TextBlockCronograma.Visibility = Visibility.Visible;
                this.TextBlockWebSite.Visibility = Visibility.Visible;
            }
        }

        private void AppBarButton_Click(object sender, RoutedEventArgs e)
        {
        }

        private void GridMapa_Tapped(object sender, TappedRoutedEventArgs e)
        {
            Frame.Navigate(typeof(MapView));
        }

        private void GridCrograma_Tapped(object sender, TappedRoutedEventArgs e)
        {
            Frame.Navigate(typeof(ScheduleView));
        }

        private void GridHistoria_Tapped(object sender, TappedRoutedEventArgs e)
        {
            Frame.Navigate(typeof(HistoryView));
        }
        
    }
}
