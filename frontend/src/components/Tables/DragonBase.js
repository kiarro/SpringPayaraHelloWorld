import './DragonBase.css'
import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import cellEditFactory from 'react-bootstrap-table2-editor';
import filterFactory, { textFilter, selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import { Type } from 'react-bootstrap-table2-editor';
import HeaderButton from './../../components/Buttons/HeaderButton';

import ErrorMessage from '../ErrorMessage/ErrorMessage';

const defaultSorted = [{
  dataField: 'id',
  order: 'desc'
}];

const selectRow = {
  mode: 'checkbox',
  // //clickToSelect: true,
  clickToEdit:true,
  hideSelectAll: true,
  //selectColumnPosition: 'right',
};

const customTotal = (from, to, size) => (
  <span className="react-bootstrap-table-pagination-total">
    <HeaderButton name="Удалить"/>
  </span>
);

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

const headers = [{
  dataField: 'id',
  text: 'ID',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите id'
    }
  )
}, {
  dataField: 'name',
  text: 'ИМЯ',
  sort: true,
  filter: textFilter({
    placeholder: 'Введите имя',  // custom the input placeholder
    //style: { backgroundColor: '#6b63b5' },
    className: 'my-custom-text-filter' // custom classname on input

  })
}, {
  dataField: 'age',
  text: 'ВОЗРАСТ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите возраст'
    }
  )
}, {
  dataField: 'weight',
  text: 'ВЕС',
  sort: true,
  filter: numberFilter(
    {
      style: { weight: 10 },
      placeholder: 'Введите вес'
    }
  )
}, {
  dataField: 'character',
  text: 'ХАРАКТЕР',
  sort: true,
  filter: selectFilter({
    options: selectCharacters,
    placeholder: 'Выберите характер'
  }),
  editor: {
    type: Type.SELECT,
    options: Object.entries(selectCharacters).map(([key, value]) => {
      return {
        value: key,
        label: value
      }
    })
  }
}, {
  dataField: 'type',
  text: 'ТИП',
  sort: true,
  filter: selectFilter({
    options: selectTypes,
    placeholder: 'Выберите тип'
  }),
  editor: {
    type: Type.SELECT,
    options: Object.entries(selectTypes).map(([key, value]) => {
      return {
        value: key,
        label: value
      }
    })
  }
}, {
  dataField: 'coordinates.x',
  text: 'Х',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите Х'
    }
  )
}, {
  dataField: 'coordinates.y',
  text: 'У',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите У'
    }
  )
}, {
  dataField: 'cave.depth',
  text: 'ГЛУБИНА ПЕЩЕРЫ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите глубину пещеры'
    }
  )
}, {
  dataField: 'cave.numberOfTreasures',
  text: 'КОЛИЧЕСТВО СОКРОВИЩ',
  sort: true,
  filter: numberFilter(
    {
      placeholder: 'Введите количество сокровищ'
    }
  )
}
];

class DragonBase extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null
    };
  }

  render() {

    const options = {
    sizePerPage: 5,
    nextPageText: '>',
    prePageText: '<',
    hideSizePerPage: true,
    hidePageListOnlyOnePage: true,
    showTotal: true,
    paginationTotalRenderer: customTotal,
    sizePerPageList: [{
      text: '5', value: 5
    }, {
      text: '10', value: 10
    }, {
      text: 'All', value: this.props.rows.length
    }] 
    }

    return (
      <div>
        //<div style="overflow: scroll;"> 
      <BootstrapTable
          keyField='id' data={this.props.rows} columns={headers}
          pagination={paginationFactory(options)}
          defaultSorted={defaultSorted}
          filter={filterFactory()}
          cellEdit={cellEditFactory({
            mode: 'click',
            afterSaveCell: (oldv, newv, row, col) => {
              console.log(row);
              // console.log(col);
              fetch("/dragonscaves/" + row.id, {
                method: 'PUT',
                body: JSON.stringify(row),
                headers: {
                  'Content-Type': 'application/json',
                },
              }).then(res => {
                console.log(res.status);
                if (!res.ok){
                  this.state.error = res.status;
                }
              });
            }
          })}
          selectRow={selectRow}
        rowStyle={ {overflow:'scroll' } }
      />
        <ErrorMessage text={this.state.error} />
      </div>
      //</div>

    );
  }
}


export default DragonBase;