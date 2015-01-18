using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.ViewModel
{
    public class SectorsVM : NotificationService
    {
        private Sector _sector;
        public Sector Sector
        {
            get { return _sector; }
            set { SetProperty(ref _sector, value); }
        }

        private string _name;
        public string Name
        {
            get { return _name; }
            set
            {
                SetProperty(ref _name, value);
                GetSectors();
            }
        }

        public SectorsVM()
        {
            GetSectors();
        }

        private async void GetSectors()
        {
            Sector = await Temp.Service.GetAsociation(Name);
        }
    }
}
