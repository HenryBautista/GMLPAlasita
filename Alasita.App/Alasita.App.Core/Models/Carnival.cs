using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.Core.Models
{
    //Esta clase basicamente es donde se aglomera todos la informacion que tengamos
    public class Carnival
    {
        private string carnivalName;    //Nombre de la Feria, en este caso Alasitas 2015
        private string carnivalBannerImageUrl;     // Url de la Imagen Promocional
        private List<string> carnivalAdditionalImage;     // Imagenes adicionales como fotos de de la feria
        private List<Sector> carnivalSectors;       //Lista de sectores
        private string carnivalDescription;     //Descripcion de la feria

        public string CarnivalDescription
        {
            get { return carnivalDescription; }
            set { carnivalDescription = value; }
        }
        
        public List<Sector> CarnivalSectors
        {
            get { return carnivalSectors; }
            set { carnivalSectors = value; }
        }
        
        public List<string> CarnivalAdditionalImage
        {
            get { return carnivalAdditionalImage; }
            set { carnivalAdditionalImage = value; }
        }
        
        public string CarnivalBannerImageUrl
        {
            get { return carnivalBannerImageUrl; }
            set { carnivalBannerImageUrl = value; }
        }
        
        public string CarnivalName
        {
            get { return carnivalName; }
            set { carnivalName = value; }
        }
        
    }
}
