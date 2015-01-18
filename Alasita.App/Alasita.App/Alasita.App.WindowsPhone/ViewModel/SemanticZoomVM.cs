using Alasita.App.Services;
using Alasita.App.Temp.Class;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alasita.App.ViewModel
{
    public class SemanticZoomVM : NotificationService
    {
        private List<GroupInfoList<object>> _groups;
        public List<GroupInfoList<object>> Groups
        {
            get { return _groups; }
            set { SetProperty(ref _groups, value); }
        }

        private List<Sector> _collection;
        public List<Sector> Collection
        {
            get { return _collection; }
            set { _collection = value; }
        }

        public SemanticZoomVM()
        {
            Load();
        }

        public async void Load()
        {
            Collection = await Temp.Service.GetSectors();
            List<GroupInfoList<object>> temp = new List<GroupInfoList<object>>();
            foreach (var item in Collection)
            {
                GroupInfoList<object> info = new GroupInfoList<object>();
                info.Key = item.Inicial;
                info.Count = item.Asociaciones.Count;
                foreach (var i in item.Asociaciones)
                {
                    info.Add(i.Nombre);
                }
                temp.Add(info);
            }
            Groups = temp;
        }

        public class GroupInfoList<T> : List<object>
        {

            public string Key { get; set; }
            public int Count { get; set; }
            public bool Enable { get; set; }

            public new IEnumerator<object> GetEnumerator()
            {
                return (System.Collections.Generic.IEnumerator<object>)base.GetEnumerator();
            }
        }
    }
}
