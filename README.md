<br>
<h4>Projekt przygotowany w ramach warsztatu z 3. modułu kursu Java w Coder's Lab</h4>

<h2><b>Opis klas:</b></h2>

<h5>Klasy modelu:</h5>

<li><b>DBUtil</b> - klasa odpowiedzialna za połączenie z bazą danych</li>
<li><b>User</b> - klasa reprezntująca tabelę users z bazy danych workshop2</li>
<li><b>UserDAO</b> - klasa pośrednicząca między klasą User, a bazą danych</li>
<br>
<h5>Klasy kontrolera:</h5>
<li><b>Add</b> - servlet odpowiedzialny za dodawanie nowego użytkownika do bazy </li>
<li><b>AdminSettings</b> - servlet odpwiedzialny za edycję danych administratora</li>
<li><b>Delete</b> - servlet odpwiedzialny za usunięcie użytkownika z bazy</li>
<li><b>Edit</b> - servlet odpwiedzialny za edycję danych użytkownika</li>
<li><b>ListUsers</b> - servlet odpwiedzialny za wyświetlanie listy użytkowników / wyników wyszukiwania</li>
<li><b>Login</b> - servlet odpwiedzialny za logowanie się administratora</li>
<li><b>Logout</b> - servlet odpwiedzialny za wylogowywanie się administratora</li>
<li><b>Show</b> - servlet odpwiedzialny za wyświetlenie pojedyńczego użytkowania</li>
<br>
<h5>Pliki widoku:</h5>
<li><b>add.jsp</b> - plik widoku servletu Add</li>
<li><b>adminSettings.jsp</b> - plik widoku servletu AdminSettings</li>
<li><b>edit.jsp</b> - plik widoku servletu Edit</li>
<li><b>list.jsp</b> - plik widoku servletu ListUsers</li>
<li><b>login.jsp</b> - plik widoku servletu Login</li>
<li><b>show.jsp</b> - plik widoku servletu Show</li>
<li><b>footer.jsp</b> - wspólny dla plików widoku kod stopki</li>
<li><b>sidebar.jsp</b> - wspólny dla plików widoku kod paska nawigacyjnego</li>
<li><b>topbar.jsp</b> - wspólny dla plików widoku kod nagłówka</li>
<br>
<h5>Filtry:</h5>
<li><b>FilterEncoding</b> - filtr nadający żądaniom kodowanie oraz typ zawartości</li>
<li><b>FilterAuth</b> - filtr weryfikujący stan zalogowania administratora</li>
<br>
<h5>Pliki pomocnicze:</h5>

<li><b>workshop2_dump.sql</b> - dump do stworzenia bazy danych używanej przez projekt</li>
<li><b>FillTable</b> - klasa ładująca do bazy przykładowe rekordy</li>
