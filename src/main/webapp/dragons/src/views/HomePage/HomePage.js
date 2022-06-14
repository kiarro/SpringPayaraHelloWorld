import React from 'react';
import './HomePage.css';
import DragonBase from './../../components/Tables/DragonBase'
import HeaderButton from './../../components/Buttons/HeaderButton';
import BaseInput from './../../components/Inputs/BaseInput';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useRouteMatch,
  useParams,
} from 'react-router-dom';


const headers = ['ID', 'ИМЯ', 'ВОЗРАСТ', 'ВЕС','ХАРАКТЕР','ТИП','Х','У','ГЛУБИНА ПЕЩЕРЫ','КОЛИЧЕСТВО СОКРОВИЩ'];

const rows = [
  ['1', 'Балерион', '208', '10','Хаос','Огненный','12','4','1400','157'],
  ['2', 'Вхагар', '181', '18','Хаос','Огненный','12','4','1400','157'],
  ['3', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
  ['4', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
  ['5', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
];

function HomePage()
{
  return (
    <div>
    <main>
    <DragonBase rows={rows} headers={headers} rowHeaders page="1 из я хуй знает скольки" />
    </main>
    <footer>
    <ul className='NeededOperations'>
    <li>
    <BaseInput 
    name="Массив драконов, имя которых начинается с:" 
    enter="Введите подстроку имени"
    />
    </li>
    <li><Link to="/returnarray"><HeaderButton name="Вернуть"/></Link></li>
    </ul>
    <ul className='NeededOperations'> 
    <li>
    <BaseInput 
    name="Количество драконов, характер которых больше:" 
    enter="Введите характер"
    />
    </li>
    <li><Link to='/charactercount'> <HeaderButton name="Вернуть"/></Link> </li>
    </ul>
    <ul className='NeededOperations'>
    <li>
    <BaseInput 
    name="Количество драконов,тип которых меньше:" 
    enter="Введите тип"
    />
    </li>
    <li><Link to='/typecount'> <HeaderButton name="Вернуть"/> </Link></li>
    </ul> 
    </footer>
    </div>
  );
}

export default HomePage;