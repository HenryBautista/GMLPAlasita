using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.Core.Models
{
    public class History
    {
        private string title;
        private string description;
        private List<string> images;

        public string Title
        {
            get { return title; }
            set { title = value; }
        }

        public string Description
        {
            get { return description; }
            set { description = value; }
        }

        /// <summary>
        /// Si existen images que mostra se guardaran en esta lista para cada objeto instanciado
        /// </summary>
        public List<string> Images
        {
            get { return images; }
            set { images = value; }
        }

    }
}
