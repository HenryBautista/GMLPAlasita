﻿

#pragma checksum "C:\Users\Yolger\Documents\GitHub\GMLPAlasita\Alasita.App\Alasita.App\Alasita.App.WindowsPhone\Views\Search.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "F1545E7C8B8F56117B0BB52875E796CB"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Alasita.App.Views
{
    partial class Search : global::Windows.UI.Xaml.Controls.Page, global::Windows.UI.Xaml.Markup.IComponentConnector
    {
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 4.0.0.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
 
        public void Connect(int connectionId, object target)
        {
            switch(connectionId)
            {
            case 1:
                #line 167 "..\..\Views\Search.xaml"
                ((global::Windows.UI.Xaml.Controls.AutoSuggestBox)(target)).TextChanged += this.search_TextChanged;
                 #line default
                 #line hidden
                break;
            }
            this._contentLoaded = true;
        }
    }
}


