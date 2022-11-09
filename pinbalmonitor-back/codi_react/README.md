# Components
- Servei.  Té com a propietats un títol i el llistat amb la informació de les peticions, les quals són importades des del fitxer src/components/serveis/data/dadesServeis.js. Conté també l'estat d'entorn, que pot ser modificat des del component fill OpcionsEntorn.

<p align="center">
  <img width="50%" alt="Component Servei" src="
doc-react/servei.PNG">
</p>
  
- Petició. Component fill de serveis. Té la propietat de nom i la d'entorn, que provenen de les dades de component pare, Servei. L'entorn servirà per saber a quina funció del controlador cridar. Té 'estat de loading (boleà), resultatProva ("èxit" o "fall") i data d'última execució.
  
 <p align="center">
  <img width="50%" alt="Component Petició" src="doc-react/peticio.PNG">
</p>

- Opcions Entorn. Component fill de Servei. Serveix per cambiar la propietat d'entorn a "proves" o a "producció". Aquesta propietat forma part del component servei i és passada a totes les peticions depenents del servei.
  
 <p align="center">
  <img alt="Component Opcions Entorn" src="doc-react/opcionsEntorn.PNG">
</p>

- Snackbar Resultat. Té la propietat boleana d'obert. Aquestá és posada a true per part del component pare Petició quan la petició deixa d'estar carregant. El text i color canvien en funció de si la propietat resultatProva (que és passada per props a través del component pare Petició) és "èxit" o "fall"
  
 <p align="center">
  <img  width="50%" alt="Component Snackbar resultat" src="doc-react/snackbar-resultat.PNG">
</p>

- Navbar. Conté el menú de navegació. Es pinta el llistat de rutes importat des de  src/router/rutesServeis.js.
  
 <p align="center">
  <img  width="50%" alt="Component Navbar" src="doc-react/navbar.PNG">
</p>

- Toggle Color canvia el tema de colors mitjaçant la propietat tema, que pot ser clar o fosc. Si el valor és "fosc" s'afageix la classe fosc a la pseudoclasse root del css i se'n aplica la paleta de colors corresponent (Layout.css).
  
<p align="center">
  <img alt="Component ToggleColor" src="doc-react/color.PNG">
</p>

   - Botó Scroll up té la propietat booleana "visible" (canvia en funció de si l'usuari ha fer scroll cap avall) i el manejador d'events ScrollToTop per a què en clicar en el component, la pàgina es vegi des del principi.
  
<p align="center">
  <img alt="Botó Scrollup" src="doc-react/scrollUp.PNG">
</p>
# Estructura de l'aplicació

<p align="center">
  <img alt="Diagrama Aplicació" src="doc-react/PinbalMonitorReact.png">
</p>


# Com afegir un nou servei

1. Afegir les dades d'un servei:
   A la ruta src/components/serveis/data/dadesServeis.js crear la constant seguint l'estructura d'exemple.

<p align="center">
  <img alt="Exemple de com afegir dades d'un servei" src="dadesServeis-afegir.PNG">
</p>

2. Exportar la constant creada al pas anterior:


<p align="center">
  <img alt="Exemple d'exportar la constant" src="export.PNG">
</p>

1. Afegir servei a l'enrutador per a crear la ruta i la opció en el menú:
   Al fitxer src/router/rutesServeis.js, a la constant rutesServeis afegir un objecte amb els camps servei(adreçant a la constant creada en el pas 1 al fitxer de dadesServeis), el camp path (elegir valor del path a la url) i el camp nom a menú.


<p align="center">
  <img alt="Exemple d'afegir la ruta a la variable de dades de l'enrutador" src="enrutador-exemple.PNG">
</p>
Fet això l'enrutador directament crearà una ruta al path especificat que renderitzarà la vista Layout amb les dades del servei (especificades a src/components/serveis/data/dadesServeis.js)

<p align="center">
  <img alt="Com l'enrutador crea la vista" src="enrutador vista.PNG">
</p>

El Layout passarà la prop servei al component Servei, d'aquesta manera aquest coneix quin és el seu títol i quines són les seves peticions.
<p align="center">
  <img alt="Com el Layout renderitza el servei amb les dades corresponents" src="Layout servei.PNG">
</p>

Després d'haver seguit els passos el resultat de la vista de la ruta /exemple (la que s'ha especificat a la propietat path de l'enrutador) será:

<p align="center">
  <img alt="Resultat que quedaria seguint les pases de l'exemple" src="resultat-exemple.PNG">
</p>
