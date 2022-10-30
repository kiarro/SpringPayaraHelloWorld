import React from 'react';
// import './HomePage.css';
import DragonBase_back from './../../components/Tables/DragonBase_back'
import HeaderButton from './../../components/Buttons/HeaderButton';
import BaseInput from './../../components/Inputs/BaseInput';
import Select from './../../components/Inputs/Select';

import filterFactory, { textFilter, selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useRouteMatch,
  useParams,
} from 'react-router-dom';

import {
  useEffect,
  useState
} from 'react'
import ErrorMessage from '../../components/ErrorMessage/ErrorMessage';

const selectTypes = {
  WATER: 'WATER',
  UNDERGROUND: 'UNDERGROUND',
  AIR: 'AIR',
  FIRE: 'FIRE'
};

const selectCharacters = {
  CUNNING: 'CUNNING',
  WISE: 'WISE',
  CHAOTIC: 'CHAOTIC',
  CHAOTIC_EVIL: 'CHAOTIC_EVIL',
  FICKLE: 'FICKLE'
};


function DragonTable_back() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [dragons, setDragons] = useState([]);

  const [namestarts, setNamestarts] = useState("");
  const [charactermore, setCharactermore] = useState("CUNNING");
  const [typeless, setTypeless] = useState("WATER");

  return (
    <div>
      <main>
        <DragonBase_back />
      </main>
    </div>
  );
  // }
}

export default DragonTable_back;
