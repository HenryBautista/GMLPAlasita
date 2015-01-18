using System;
using System.Collections.Generic;
using System.Text;

namespace Alasita.App.Temp.Class
{
    public class Evento
    {
        public string Fecha { get; set; }
        public string Actividad { get; set; }
        public string Lugar { get; set; }
        public List<string> Hora { get; set; }
        public string Organizador { get; set; }
    }
}
