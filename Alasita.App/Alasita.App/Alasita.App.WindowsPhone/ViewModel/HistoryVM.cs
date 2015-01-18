using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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

        public HistoryVM()
        {
            GetHistory();
        }

        public async void GetHistory()
        {
            Historia = await Temp.Service.GetHistory();
        }
    }
}