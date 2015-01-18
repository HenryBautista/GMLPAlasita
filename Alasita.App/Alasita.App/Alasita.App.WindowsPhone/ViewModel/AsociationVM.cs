using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.ViewModel
{
    public class AsociationVM : NotificationService
    {
        private Asociacion _asociacion;
        public Asociacion Asociacion
        {
            get { return _asociacion; }
            set { SetProperty(ref _asociacion, value); }
        }

        private string _name;
        public string Name
        {
            get { return _name; }
            set
            {
                SetProperty(ref _name, value);
                GetAsociation();
            }
        }

        public AsociationVM()
        {
            GetAsociation();
        }

        private async void GetAsociation()
        {
            if (Name != null)
                Asociacion = await Temp.Service.GetAsociations(Name);
        }
    }
}
