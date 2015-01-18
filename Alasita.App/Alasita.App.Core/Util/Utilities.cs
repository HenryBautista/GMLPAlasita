using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Alasita.App.Core.Models;
using Windows.Storage;
using Newtonsoft.Json;
using Alasita.App.Core.Data;
namespace Alasita.App.Core.Util
{
    public static class Utilities
    {
        /// <summary>
        /// Devuelve un objeto Carnival Cargado desde el paquete instalado de la aplicacion
        /// </summary>
        /// <param name="filename"></param>
        /// <returns></returns>
        public static async Task<Carnival> LoadFromLocalJson(string filename)
        {
            var folder = Windows.Storage.ApplicationData.Current.LocalFolder;
            StorageFile file;
            try
            {
                file = await folder.GetFileAsync(filename);
                var json = await Windows.Storage.FileIO.ReadTextAsync(file);
                Carnival schedules = JsonConvert.DeserializeObject<Carnival>(json);
                return schedules;
            }
            catch (Exception)
            {
                return new Carnival();
            }

        }

        /// <summary>
        /// Devuelve Un objeto Carnival carrgado desde la carpeta JsonFiles
        /// </summary>
        /// <param></param>
        /// <returns></returns>
        public static async Task<Carnival> LoadTheLastHope()
        {
            var folder = Windows.ApplicationModel.Package.Current.InstalledLocation;
            folder = await folder.GetFolderAsync("JsonFiles");
            var file = await folder.GetFileAsync("Carnival.json");
            var json = await Windows.Storage.FileIO.ReadTextAsync(file);
            Carnival hope = JsonConvert.DeserializeObject<Carnival>(json);
            return hope;
        }
        public static async void SaveCarnivalToJson(string filename,Carnival carnival)
        {
            var folder = Windows.Storage.ApplicationData.Current.LocalFolder;
            var file = await folder.CreateFileAsync(filename, Windows.Storage.CreationCollisionOption.ReplaceExisting);
            var json = JsonConvert.SerializeObject(carnival);
            await Windows.Storage.FileIO.WriteTextAsync(file, json);
        }

        public static List<Sector> SearchProduct(string query) {
            List<Sector> respose = new List<Sector>();
            foreach (var sector in DataProvider.StaticCarnival.CarnivalSectors)
            {
                if (sector.Tags.Contains(query))
                {
                    respose.Add(sector);
                }
            }
            return respose;
        }
    }
}
