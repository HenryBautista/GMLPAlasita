using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Alasita.App.Core.Models;
using Windows.Storage;
using Newtonsoft.Json;
namespace Alasita.App.Core.Util
{
    public static class Utilities
    {
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

        public static async void SaveCarnivalToJson(string filename,Carnival carnival)
        {
            var folder = Windows.Storage.ApplicationData.Current.LocalFolder;
            var file = await folder.CreateFileAsync(filename, Windows.Storage.CreationCollisionOption.ReplaceExisting);
            var json = JsonConvert.SerializeObject(carnival);
            await Windows.Storage.FileIO.WriteTextAsync(file, json);
        }
    }
}
