import React from 'react';
import './HomePage.css';
import DragonBase from './../../components/Tables/DragonBase'
import HeaderButton from './../../components/Buttons/HeaderButton';
import BaseInput from './../../components/Inputs/BaseInput';
import filterFactory, { textFilter,selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useRouteMatch,
  useParams,
} from 'react-router-dom';

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
  dataField:'x',
  text:'Х',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите Х'
    }
  )},{
  dataField:'y',
  text:'У',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите У'
    }
  )},{
  dataField:'cave',
  text:'ГЛУБИНА ПЕЩЕРЫ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите глубину пещеры'
    }
  )},{
  dataField:'treasure',
  text:'КОЛИЧЕСТВО СОКРОВИЩ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите количество сокровищ'
    }
  )}
];


const rows = [
{id:'1',name: 'Балерион',age: '208',weight: '10',character:'CHAOTIC_EVIL',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'2',name: 'Вхагар',age: '181',weight: '18',character:'FICKLE',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'3',name: 'Мераксес',age:'181',weight: '18',character:'CUNNING',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'4',name: 'Мераксес',age:'181',weight: '18',character:'WISE',type:'AIR',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'5',name: 'Мераксес',age:'181',weight: '18',character:'CHAOTIC',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'6',name: 'Балерион',age: '208',weight: '10',character:'CHAOTIC_EVIL',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'7',name: 'Вхагар',age: '181',weight: '18',character:'FICKLE',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'8',name: 'Мераксес',age:'181',weight: '18',character:'CUNNING',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'9',name: 'Мераксес',age:'181',weight: '18',character:'WISE',type:'AIR',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'10',name: 'Мераксес',age:'181',weight: '18',character:'CHAOTIC',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'11',name: 'Балерион',age: '208',weight: '10',character:'CHAOTIC_EVIL',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'12',name: 'Вхагар',age: '181',weight: '18',character:'FICKLE',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'13',name: 'Мераксес',age:'181',weight: '18',character:'CUNNING',type:'FIRE',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'14',name: 'Мераксес',age:'181',weight: '18',character:'WISE',type:'AIR',x:'12',y:'4',cave:'1400',treasure:'157'},
{id:'15',name: 'Мераксес',age:'181',weight: '18',character:'CHAOTIC',type:'WATER',x:'12',y:'4',cave:'1400',treasure:'157'}
];

function HomePage()
{
  return (
    <div>
    <main>
    <DragonBase rows={rows} headers={headers} rowHeaders page="1 из я хуй знает скольки" />
    </main>
    <footer>
    <ul>
      <li className='OperationItem'>
    <ul className='NeededOperations'>
    <li>
    <BaseInput 
    name="Массив драконов, имя которых начинается с:" 
    enter="Введите подстроку имени"
    />
    </li>
    <li><Link to="/returnarray"><HeaderButton name="Вернуть"/></Link></li>
    </ul>
    </li>
      <li className='OperationItem'>
    <ul className='NeededOperations'> 
    <li>
    <BaseInput 
    name="Количество драконов, характер которых больше:" 
    enter="Введите характер"
    />
    </li>
    <li><Link to='/charactercount'> <HeaderButton name="Вернуть"/></Link> </li>
    </ul>
    </li>
    <li className='OperationItem'>
    <ul className='NeededOperations'>
    <li>
    <BaseInput 
    name="Количество драконов,тип которых меньше:" 
    enter="Введите тип"
    />
    </li>
    <li><Link to='/typecount'> <HeaderButton name="Вернуть"/> </Link></li>
    </ul> 
    </li>   
    </ul>
    
    </footer>
    </div>
  );
}

export default HomePage;
