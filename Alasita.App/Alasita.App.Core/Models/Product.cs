using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.Core.Models
{
    public class Product
    {
        private string productName; //Nombre del producto 
       // private int productKey; //Clave Numerica Ejem:1     2       3
        private string productImage;    // URL de la Imagen del Producto si es local "appx:///Images/nombreArchivo"
        //private string productDescription;  //  !!! Esta  variable puede ser eliminada si es que no se nos diera informacion !!!

        //public string ProductDescription
        //{
        //    get { return productDescription; }
        //    set { productDescription = value; }
        //}
        
        public string ProductImage
        {
            get { return productImage; }
            set { productImage = value; }
        }
        
        //public int ProductKey
        //{
        //    get { return productKey; }
        //    set { productKey = value; }
        //}
        
        public string ProductName
        {
            get { return productName; }
            set { productName = value; }
        }
        
    }
}
