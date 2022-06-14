import './App.css';
import dragon from './dragon.png'
import React from 'react';
import HeaderButton from './components/Buttons/HeaderButton';
import AddDragon from './views/AddDragon/AddDragon';
import ReturnCount from './views/ReturnCount/ReturnCount';
import HomePage from './views/HomePage/HomePage'
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useRouteMatch,
  useParams,
} from 'react-router-dom';
import ReturnArray from './views/ReturnArray/ReturnArray';

function App() {

  return (
    <div className="App">
      <body>
        <Router>
        <header className="App-header">
        <div className='App-logo'><Link to="/"><img src={dragon} className="App-logo" alt="logo" /></Link></div>
        <nav className='ButtonContainer'> 
        <Link to="/adddragon"><HeaderButton name="Добавить дракона"/></Link>
        </nav>      
        </header>
        <Routes>
        <Route path='/' element={<HomePage/>} />
        <Route path='/adddragon' element={<AddDragon/>} />
        <Route path='/charactercount' element={<ReturnCount type="характер которых которых больше 1" count="5"/>} />
        <Route path='/typecount' element={<ReturnCount type="тип которых которых меньше 1" count="5"/>} />
        <Route path='/returnarray' element={<ReturnArray namesubstring="Мераксес"/>} />
        </Routes>
        </Router>
        </body>
    </div>
  );
  
}

export default App;


