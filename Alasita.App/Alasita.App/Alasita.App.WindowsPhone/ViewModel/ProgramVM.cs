using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.ViewModel
{
    public class ProgramVM : NotificationService
    {
        private Programa _programa;
        public Programa Programa
        {
            get { return _programa; }
            set { SetProperty(ref _programa, value); }
        }

        public ProgramVM()
        {
            GetPrograma();
        }

        public async void GetPrograma()
        {
            Programa = await Temp.Service.GetProgram();
        }
    }
}
