import React from 'react';
import './ReturnArray.css';
import filterFactory, { textFilter,selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import DragonBase from './../../components/Tables/DragonBase';
import { useNavigate, useSearchParams } from 'react-router-dom';

import {
  useEffect,
  useState
} from 'react'

const selectTypes = {
  WATER: 'WATER',
  UNDERGROUND:'UNDERGROUND',
  AIR:'AIR',
  FIRE:'FIRE'
};

const selectCharacters = {
  CUNNING: 'CUNNING',
  WISE: 'WISE',
  CHAOTIC: 'CHAOTIC',
  CHAOTIC_EVIL: 'CHAOTIC_EVIL',
  FICKLE: 'FICKLE'
};

const headers = [{
  dataField: 'id',
  text:'ID',
  sort: true,
  filter: numberFilter(
    {
    placeholder: 'Введите id'
    }
  )},{
  dataField:'name',
  text:'ИМЯ',
  sort: true,
  filter: textFilter({
    placeholder: 'Введите имя',  // custom the input placeholder
    //style: { backgroundColor: '#6b63b5' },
    className: 'my-custom-text-filter' // custom classname on input
    
  })
},{
  dataField:'age',
  text:'ВОЗРАСТ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите возраст'
    }
  )},{
  dataField:'weight',
  text:'ВЕС',
  sort: true,
  filter: numberFilter(
    {
      style: { weight: 10 },
      placeholder: 'Введите вес'
    }
  )},{
  dataField:'character',
  text:'ХАРАКТЕР',
  sort: true,
  filter: selectFilter({
    options: selectCharacters,
    placeholder: 'Выберите характер'
  })},{
  dataField:'type',
  text:'ТИП',
  sort: true,
  filter: selectFilter({
    options: selectTypes,
    placeholder: 'Выберите тип'
  })},{
  dataField:'coordinates.x',
  text:'Х',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите Х'
    }
  )},{
  dataField:'coordinates.y',
  text:'У',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите У'
    }
  )},{
  dataField:'cave.depth',
  text:'ГЛУБИНА ПЕЩЕРЫ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите глубину пещеры'
    }
  )},{
  dataField:'cave.numberOfTreasures',
  text:'КОЛИЧЕСТВО СОКРОВИЩ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите количество сокровищ'
    }
  )}
];

function ReturnArray(props)
{
  const [searchParams, setSearchParams] = useSearchParams();
  const name = searchParams.get("name");

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [dragons, setDragons] = useState([]);

  useEffect(() => {
    fetch("/dragonscaves/namestarts?name="+name)
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setDragons(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [])

  return (
    <div>
    {/* <header><h1>Массив драконов {name}:</h1></header> */}
    <main>
    <DragonBase rows={dragons} headers={headers} rowHeaders page="1 из я хуй знает скольки" />
    </main>
    </div>
  );
}

export default ReturnArray;