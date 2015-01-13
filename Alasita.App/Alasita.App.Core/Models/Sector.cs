using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.Core.Models
{
    public class Sector
    {
        private string sectorName;  //Nombre del Sector
        private string sectorKey;   //Clave del sector Ejem A, B, C,etc.
        private string sectorDescription;   //Tengo que escribirlo?
        private string sectorMapImage;  //La Imagen es el pedazo correspondiente del sector en el mapa (un URL) si es local "appx:///Images/nombreArchivo"
        private List<Association> sectorAssociations;   //lista de associaciones del sector
            
        public List<Association> SectorAssociations
        {
            get { return sectorAssociations; }
            set { sectorAssociations = value; }
        }
        
        public string SectorMapImage
        {
            get { return sectorMapImage; }
            set { sectorMapImage = value; }
        }
        
        public string SectorDescription
        {
            get { return sectorDescription; }
            set { sectorDescription = value; }
        }
        
        public string SectorKey
        {
            get { return sectorKey; }
            set { sectorKey = value; }
        }
        
        public string SectorName
        {
            get { return sectorName; }
            set { sectorName = value; }
        }
        
    }
}
