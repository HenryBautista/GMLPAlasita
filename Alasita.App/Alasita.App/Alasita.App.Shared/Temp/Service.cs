using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using Alasita.App.Temp.Class;
using Windows.Storage;
using Windows.Data.Json;

namespace Alasita.App.Temp
{
    public class Service
    {
        private static Service service = new Service();

        private Alasitas _alasita;
        public Alasitas alasita
        {
            get { return _alasita; }
        }

        public static async Task<Asociacion> GetAsociations(string name)
        {
            await service.GetServiceAsync();
            foreach (var item in service.alasita.Sector)
            {
                foreach (var i in item.Asociaciones)
                {
                    if (i.Nombre == name)
                    {
                        return i;
                    }
                }
            }
            return null;
        }

        public static async Task<Sector> GetAsociation(string key)
        {
            await service.GetServiceAsync();
            foreach (var item in service.alasita.Sector)
            {
                if (item.Inicial == key)
                {
                    return item;
                }
            }
            return null;
        }

        // Obtiene una lista de Parrafos
        public static async Task<List<Parrafo>> GetHistory()
        {
            await service.GetServiceAsync();
            return service.alasita.Historia;
        }

        // Obtiene una lista de los Sectors, con sus respectivas asociaciones y productos de cada asociación
        public static async Task<List<Sector>> GetSectors()
        {
            await service.GetServiceAsync();
            return service.alasita.Sector;
        }

        // Obtiene una lista de Asociaciones a partir de una cadena de referencia del producto a buscar
        public static async Task<List<Asociacion>> GetSearchAssociation(string cadena)
        {
            await service.GetServiceAsync();
            List<Asociacion> association = new List<Asociacion>();
            foreach (var _sec in service.alasita.Sector)
            {
                foreach (var _assoc in _sec.Asociaciones)
                {
                    foreach (var _prod in _assoc.Productos)
                    {
                        if (_prod.ToLower().Contains(cadena.ToLower()))
                        {
                            association.Add(_assoc);
                            break;
                        }
                    }
                }
            }
            return association;
        }

        // Obtiene una lista de productos a partir de una cadena de referencia del producto a buscar
        public static async Task<List<string>> GetProducts(string cadena)
        {
            await service.GetServiceAsync();
            List<string> productos = new List<string>();
            foreach (var _sec in service.alasita.Sector)
            {
                foreach (var _assoc in _sec.Asociaciones)
                {
                    foreach (var _prod in _assoc.Productos)
                    {
                        if (!productos.Contains(_prod) && _prod.ToLower().Contains(cadena.ToLower()))
                            productos.Add(_prod);
                    }
                }
            }
            productos = productos.OrderBy(item => item).ToList();
            return productos;
        }

        // Obtiene una lista con los eventos
        public static async Task<Programa> GetProgram()
        {
            await service.GetServiceAsync();
            return service.alasita.Programa;
        }

        // Metodo asincrono que se ejecuta solo al comenzar la aplicación
        private async Task GetServiceAsync()
        {
            if (this._alasita != null)
                return;
            _alasita = new Alasitas();
            Uri dataUri = new Uri("ms-appx:///Temp/Alasita.json");
            StorageFile file = await StorageFile.GetFileFromApplicationUriAsync(dataUri);
            string jsonText = await FileIO.ReadTextAsync(file);
            JsonObject jsonObject = JsonObject.Parse(jsonText);
            // Define la historia
            List<Parrafo> historia = new List<Parrafo>();
            foreach (var item in jsonObject["Historia"].GetArray())
            {
                JsonObject temp = item.GetObject();
                Parrafo _parrafo = new Parrafo();
                _parrafo.texto = temp["Texto"].GetString();
                _parrafo.imagen = temp["Imagen"].GetString();
                historia.Add(_parrafo);
            }
            _alasita.Historia = historia;
            // Define los Sectores con sus determinadas Asociaciones y sus productos
            List<Sector> sectores = new List<Sector>();
            foreach (var item in jsonObject["Sectores"].GetArray())
            {
                JsonObject temp = item.GetObject();
                Sector sector = new Sector();
                sector.Inicial = temp["Inicial"].GetString();
                sector.Asociaciones = new List<Asociacion>();
                foreach (var value in temp["Asociaciones"].GetArray())
                {
                    JsonObject asoc = value.GetObject();
                    Asociacion asociacion = new Asociacion();
                    asociacion.Nombre = asoc["Nombre"].GetString();
                    asociacion.NroExpositores = asoc["NroExpositores"].GetString();
                    asociacion.Productos = new List<string>();
                    foreach (var prod in asoc["Productos"].GetArray())
                    {
                        asociacion.Productos.Add(prod.GetString());
                    }
                    sector.Asociaciones.Add(asociacion);
                }
                sectores.Add(sector);
            }
            _alasita.Sector = sectores;
            // Define el Programa
            Programa programa = new Programa();
            programa.Actividades = new List<Evento>();
            programa.Exposiciones = new List<Evento>();
            JsonObject obj = jsonObject["Programa"].GetArray().First().GetObject();
            foreach (var act in obj["Actividades"].GetArray())
            {
                JsonObject eve = act.GetObject();
                Evento _evento = new Evento();
                _evento.Fecha = eve["Fecha"].GetString();
                _evento.Actividad = eve["Actividad"].GetString();
                _evento.Lugar = eve["Lugar"].GetString();
                _evento.Organizador = eve["Organizador"].GetString();
                _evento.Hora = new List<string>();
                foreach (var hour in eve["Hora"].GetArray())
                {
                    _evento.Hora.Add(hour.GetString());
                }
                programa.Actividades.Add(_evento);
            }
            foreach (var act in obj["Exposiciones"].GetArray())
            {
                JsonObject eve = act.GetObject();
                Evento _evento = new Evento();
                _evento.Fecha = eve["Fecha"].GetString();
                _evento.Actividad = eve["Actividad"].GetString();
                _evento.Lugar = eve["Lugar"].GetString();
                _evento.Organizador = eve["Organizador"].GetString();
                _evento.Hora = new List<string>();
                foreach (var hour in eve["Hora"].GetArray())
                {
                    _evento.Hora.Add(hour.GetString());
                }
                programa.Exposiciones.Add(_evento);
            }
            _alasita.Programa = programa;
        }
    }
}
