using Alasita.App.Services;
using Alasita.App.Temp.Class;
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

// The User Control item template is documented at http://go.microsoft.com/fwlink/?LinkId=234236

namespace Alasita.App.UserControls
{
    public sealed partial class SemanticZoomControl : UserControl
    {

        public SemanticZoomControl()
        {
            this.InitializeComponent();
        }

        private void Grid_PointerPressed(object sender, PointerRoutedEventArgs e)
        {
            SemanticZoom.ToggleActiveView();
        }

        private void gridView_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //if (lst.SelectedItem != null && enable)
            //{
            //    PageInvocationService.Navigate("Asociation", lst.SelectedItem);
            //}
            //lst.SelectedItem = null;
        }

        private void TextBlock_Tapped(object sender, TappedRoutedEventArgs e)
        {
            TextBlock aso = sender as TextBlock;
            if (aso!= null)
            {
                PageInvocationService.Navigate("Asociation", aso.Text);
            }
        }
    }
}
