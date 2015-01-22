using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.UI.Xaml;

namespace Alasita.App.ViewModel
{
    public class HistoryVM : NotificationService
    {
        private List<Parrafo> _historia;
        public List<Parrafo> Historia
        {
            get { return _historia; }
            set { SetProperty(ref _historia, value); }
        }

        private List<string> _image;
        public List<string> Image
        {
            get { return _image; }
            set { SetProperty(ref _image, value); }
        }

        public HistoryVM()
        {
            GetHistory();
        }

        public async void GetHistory()
        {
            Historia = await Temp.Service.GetHistory();
            List<string> image = new List<string>();
            foreach (var item in Historia)
            {
                image.Add(item.imagen);
            }
            Image = image;
        }
    }
}