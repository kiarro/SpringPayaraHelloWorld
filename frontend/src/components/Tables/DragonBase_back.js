import './DragonBase.css'
import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import cellEditFactory from 'react-bootstrap-table2-editor';
import filterFactory, { textFilter, selectFilter, numberFilter, Comparator } from 'react-bootstrap-table2-filter';
import { Type } from 'react-bootstrap-table2-editor';
import ErrorMessage from '../ErrorMessage/ErrorMessage';
import HeaderButton from './../../components/Buttons/HeaderButton';


const param_map = {
  "coordinates.x": "coordinateX",
  "coordinates.y": "coordinateY",
  "cave.depth": "caveDepth",
  "cave.numberOfTreasures": "caveNumberOfTreasures",
}

const operation_map = {
  "=": "eq",
  "!=": "ne",
  ">": "gt",
  ">=": "ge",
  "<": "lt",
  "<=": "le",
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

const paginationOptions = {
  paginationSize: 4,
  pageStartIndex: 1,
  sizePerPage: 5,
  nextPageText: '>',
  prePageText: '<',
  hidePageListOnlyOnePage: true,
  showTotal: true,
  sizePerPageList: [{
    text: '5', value: 5
  }, {
    text: '10', value: 10
  }, {
    text: '25', value: 25
  }]
};

const editOptions = {
  mode: 'click',
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

    this.getData((this.state.page - 1) * this.state.sizePerPage, this.state.sizePerPage);
  }

  getData = (offset, limit, sortField, sortOrder, filters) => {
    let url = "/dragonscaves?offset=" + offset + "&limit=" + limit;
    let url_filter = "/dragonscaves/count?";

    if (filters) {
      for (let [key, value] of Object.entries(filters)) {
        var comparator; var filterValue;
        if (value.filterType == "NUMBER") {
          comparator = value.filterVal.comparator;
          filterValue = value.filterVal.number;
        } else if (value.filterType == "SELECT") {
          comparator = value.comparator;
          filterValue = value.filterVal;
        } else if (value.filterType == "TEXT") {
          comparator = "=";
          filterValue = value.filterVal;
        }
        key = param_map[key] || key;
        if (filterValue && comparator) {
          let str = "&" + key + "=" + filterValue + "&op_" + key + "=" + operation_map[comparator];
          url += str;
          url_filter += str;
        }
      }
    }

    sortField = param_map[sortField] || sortField;
    if (sortField != null) {
      url = url + "&sort=" + (sortOrder == "asc" ? "0" : "-") + sortField;
    }

    fetch(url_filter, {
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
        this.setState({ error: error.message });
      }
    )

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
        this.setState({ error: null });
      },
      (error) => {
        this.setState({ loaded: true });
        this.setState({ error: error.message });
      }
    )
  }

  updateData = (row) => {
    return fetch("/dragonscaves/" + row.id, {
      method: 'PUT',
      body: JSON.stringify(row),
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(res => {
      if (res.ok) {
        this.setState({ error: null });
      } else {
        this.setState({ error: 'HTTP status code ' + res.status });
      }
    });
  }

  handleTableChange = (type, { sortField, sortOrder, page, sizePerPage, filters, data, cellEdit }) => {
    if (type == "cellEdit") {
      let row = data.filter(x => x.id == cellEdit.rowId)[0];
      row[cellEdit.dataField] = cellEdit.newValue;
      this.updateData(row);
    }
    const currentIndex = (page - 1) * sizePerPage;
    this.setState(() => ({
      page: page,
      sizePerPage: sizePerPage,
    }));
    this.getData(currentIndex, sizePerPage, sortField, sortOrder, filters);
  }

  onDelete = () => {
    // console.log(this.state.selected);
    let id = this.state.selected[0];
    fetch("/dragonscaves/" + id, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
      }
    }).then(res => {
      if (res.ok) {
        this.setState({ data: this.state.data.filter(o => id !== o.id) });
      } else {
        this.setState({ error: res.status });
      }
    });
  }

  customTotal = (from, to, size) => (
    <span className="react-bootstrap-table-pagination-total">
      <HeaderButton name="Удалить выделенные" onClick={this.onDelete} />
    </span>
  );

  handleOnSelect = (row, isSelect) => {
    if (isSelect) {
      this.setState(() => ({
        selected: [row.id]
      }));
    } else {
      this.setState(() => ({
        selected: []
      }));
    }
  }

  render() {
    const { data, sizePerPage, page, size } = this.state;

    paginationOptions.page = page;
    paginationOptions.sizePerPage = sizePerPage;
    paginationOptions.totalSize = size;

    paginationOptions.paginationTotalRenderer = this.customTotal;

    const selectRow = {
      mode: 'checkbox',
      clickToEdit: true,
      hideSelectAll: true,
      selected: this.state.selected,
      onSelect: this.handleOnSelect,
    };

    return (
      <div>
        <BootstrapTable
          remote
          keyField="id"
          data={data}
          columns={headers}
          defaultSorted={defaultSorted}
          filter={filterFactory()}
          pagination={paginationFactory(paginationOptions)}
          onTableChange={this.handleTableChange}
          selectRow={selectRow}
          cellEdit={cellEditFactory(editOptions)}
        />
        <ErrorMessage text={this.state.error} />
      </div>
    );
  }
}

export default DragonBase_back;