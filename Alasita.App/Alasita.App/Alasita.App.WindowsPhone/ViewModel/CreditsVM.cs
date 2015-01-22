using Alasita.App.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.ViewModel
{
    public class CreditsVM : NotificationService
    {
        private List<string> _creditos;
        public List<string> Creditos
        {
            get { return _creditos; }
            set { SetProperty(ref _creditos, value); }
        }

        public CreditsVM()
        {
            GetCredits();
        }

        private async void GetCredits()
        {
            Creditos = await Temp.Service.GetCredits();
        }


    }
}
