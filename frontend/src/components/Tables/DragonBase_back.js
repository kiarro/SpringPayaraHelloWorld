import './DragonBase.css'
import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import cellEditFactory from 'react-bootstrap-table2-editor';
import filterFactory, { textFilter, selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import { Type } from 'react-bootstrap-table2-editor';
import {
  useEffect,
  useState
} from 'react'

const param_map = {
  "coordinates.x": "coordinateX",
  "coordinates.y": "coordinateY",
  "cave.depth": "caveDepth",
  "cave.numberOfTreasures": "caveNumberOfTreasures",
}


const defaultSorted = [{
  dataField: 'id',
  order: 'asc'
}];

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

const columns = [{
  dataField: 'id',
  text: 'ID',
  sort: true,
}, {
  dataField: 'name',
  text: 'ИМЯ',
  sort: true,
}, {
  dataField: 'age',
  text: 'ВОЗРАСТ',
  sort: true,
}, {
  dataField: 'weight',
  text: 'ВЕС',
  sort: true,
}, {
  dataField: 'character',
  text: 'ХАРАКТЕР',
  sort: true,
}, {
  dataField: 'type',
  text: 'ТИП',
  sort: true,
}, {
  dataField: 'coordinates.x',
  text: 'Х',
  sort: true,
}, {
  dataField: 'coordinates.y',
  text: 'У',
  sort: true,
}, {
  dataField: 'cave.depth',
  text: 'ГЛУБИНА ПЕЩЕРЫ',
  sort: true,
}, {
  dataField: 'cave.numberOfTreasures',
  text: 'КОЛИЧЕСТВО СОКРОВИЩ',
  sort: true,
}
];

const paginationOptions = {
  paginationSize: 4,
  pageStartIndex: 1,
  sizePerPage: 5,
  nextPageText: '>',
  prePageText: '<',
  // hideSizePerPage: true,
  hidePageListOnlyOnePage: true,
  showTotal: true,
  // paginationTotalRenderer: this.customTotal,
  sizePerPageList: [{
    text: '5', value: 5
  }, {
    text: '10', value: 10
  }, {
    text: '25', value: 25
  }]
};

const RemotePagination = ({ data, page, sizePerPage, onTableChange, totalSize }) => {
  paginationOptions.page = page;
  paginationOptions.sizePerPage = sizePerPage;
  paginationOptions.totalSize = totalSize;

  return (
    <div>
      <BootstrapTable
        remote
        keyField="id"
        data={data}
        columns={columns}
        defaultSorted={ defaultSorted }
        // pagination={paginationFactory({ page, sizePerPage, totalSize })}
        pagination={paginationFactory(paginationOptions)}
        onTableChange={onTableChange}
      />
    </div>
  );
}

class DragonBase_back extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      sizePerPage: 10,
      page: 1,
      size: 0,

      loaded: false,
      error: null,
    };

    fetch("/dragonscaves/count", {
      headers: {
        'Accept': 'application/json',
      }
    }).then(res => {
      if (res.ok) {
        return res.json();
      } else {
        throw { message: 'HTTP status code ' + res.status };
      }
    }).then(
      (result) => {
        this.setState({ size: result.value });
      },
      (error) => {
        this.setState({ error: error });
      }
    )

    this.getData((this.state.page - 1) * this.state.sizePerPage, this.state.sizePerPage);
  }

  getData = (offset, limit, sortField, sortOrder) => {
    sortField = param_map[sortField] || sortField;
    let url = "/dragonscaves?offset=" + offset + "&limit=" + limit;
    if (sortField != null) {
      url = url + "&sort="+(sortOrder=="asc"?"0":"-")+sortField;
    }
    fetch(url, {
      headers: {
        'Accept': 'application/json',
      }
    }).then(res => {
      if (res.ok) {
        return res.json();
      } else {
        throw { message: 'HTTP status code ' + res.status };
      }
    }).then(
      (result) => {
        this.setState({ loaded: true });
        this.setState({ data: result });
      },
      (error) => {
        this.setState({ loaded: true });
        this.setState({ error: error });
      }
    )
  }

  handleTableChange = (type, { sortField, sortOrder, page, sizePerPage }) => {
    const currentIndex = (page - 1) * sizePerPage;
    this.setState(() => ({
      page: page,
      sizePerPage: sizePerPage,
    }));
    this.getData(currentIndex, sizePerPage, sortField, sortOrder);
  }

  render() {
    const { data, sizePerPage, page } = this.state;


    return (
      <RemotePagination
        data={data}
        page={page}
        sizePerPage={sizePerPage}
        totalSize={this.state.size}
        onTableChange={this.handleTableChange}
      />
    );
  }
}

export default DragonBase_back;