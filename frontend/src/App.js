import './App.css';
import dragon from './dragon.png'
import React from 'react';
import HeaderButton from './components/Buttons/HeaderButton';
import AddDragon from './views/AddDragon/AddDragon';
import ReturnCount from './views/ReturnCount/ReturnCount';
import HomePage from './views/HomePage/HomePage';
import DragonTable_back from './views/DragonTable_back/DragonTable_back';
import Test from './views/Test';
import DragonBase_back from './components/Tables/DragonBase_back';

import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useLocation,
  useParams,
} from 'react-router-dom';
import ReturnArray from './views/ReturnArray/ReturnArray';

function BackButton() {
  let location = useLocation();
  // console.log(location);

  return (
    <nav className='ButtonContainerLeft' hidden={location.pathname=="/"}>
      <Link to=""><HeaderButton name="Назад" /></Link>
    </nav>
  );
}

function AddDragonButton() {
  let location = useLocation();
  // console.log(location);

  return (
    <nav className='ButtonContainer' hidden={location.pathname=="/adddragon"}>
      <Link to="adddragon"><HeaderButton name="Добавить дракона" /></Link>
    </nav>
  );
}

function App() {
  return (
    <div className="App">
      <div className="Body">
        <Router>
          <header className="App-header">
            <BackButton/>
            <div className='App-logo'><Link to="/"><img src={dragon} className="App-logo" alt="logo" /></Link></div>
            <AddDragonButton/>
          </header>
          <Routes>
            {/* <Route path='' element={<HomePage />} /> */}
            <Route path='/adddragon' element={<AddDragon />} />
            <Route path='/charactermore' element={<ReturnCount type="характер которых больше" param="character" />} />
            <Route path='/typeless' element={<ReturnCount type="тип которых меньше" param="type" />} />
            <Route path='/namestarts' element={<ReturnArray namesubstring="Мераксес" />} />
            <Route path='' element={<DragonTable_back />} />
            <Route path='/test' element={<Test />} />
          </Routes>
        </Router>
      </div>
    </div>
  );

}

export default App;


