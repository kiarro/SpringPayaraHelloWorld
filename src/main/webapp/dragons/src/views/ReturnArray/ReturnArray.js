import React from 'react';
import './ReturnArray.css';
import DragonBase from './../../components/Tables/DragonBase';

const headers = ['ID', 'ИМЯ', 'ВОЗРАСТ', 'ВЕС','ХАРАКТЕР','ТИП','Х','У','ГЛУБИНА ПЕЩЕРЫ','КОЛИЧЕСТВО СОКРОВИЩ'];

const rows = [
  ['1', 'Балерион', '208', '10','Хаос','Огненный','12','4','1400','157'],
  ['2', 'Вхагар', '181', '18','Хаос','Огненный','12','4','1400','157'],
  ['3', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
  ['4', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
  ['5', 'Мераксес','181', '18','Хаос','Огненный','12','4','1400','157'],
];

function ReturnArray(props)
{
  return (
    <div>
    <header><h1>Массив драконов {props.namesubstring}:</h1></header>
    <main>
    <DragonBase rows={rows} headers={headers} rowHeaders page="1 из я хуй знает скольки" />
    </main>
    </div>
  );
}

export default ReturnArray;