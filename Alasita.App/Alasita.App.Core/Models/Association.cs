using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.Core.Models
{
    public class Association
    {
        private string associationName; //Nombre de la asociacion
        private int associationKey; //Clave numerica Ejem: 1    2   3......
        //private List<string> associationInfo; //Ejem: Joyeria y plateria
        private string associationDescription;  //Tengo que escribirlo?
        private List<Product> associationProducts;  //Lista de Productos de la asociacion Ejem: gallito, billetes, canchitas, etc.
        private int expoNumber;
        private string associationImage;
        
        public string AssociationImage
        {
            get { return associationImage; }
            set { associationImage = value; }
        }
        
        public int ExpoNumber
        {
            get { return expoNumber; }
            set { expoNumber = value; }
        }
        
        public List<Product> AssociationProducts
        {
            get { return associationProducts; }
            set { associationProducts = value; }
        }
        
        public string AssociationDescription
        {
            get { return associationDescription; }
            set { associationDescription = value; }
        }
        
        //public List<string> AssociationInfo
        //{
        //    get { return associationInfo; }
        //    set { associationInfo = value; }
        //}
        
        public int AssociationKey
        {
            get { return associationKey; }
            set { associationKey = value; }
        }
        
        public string AssociationName
        {
            get { return associationName; }
            set { associationName = value; }
        }
        
    }
}
