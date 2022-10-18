import './DragonBase.css'
import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import cellEditFactory from 'react-bootstrap-table2-editor';

const defaultSorted = [{
  dataField: 'id',
  order: 'desc'
}];

const selectRow = {
  mode: 'checkbox',
  clickToSelect: true
};

const customTotal = (from, to, size) => (
  <span className="react-bootstrap-table-pagination-total">
    Страница { from } размером { to }, всего { size } драконов
  </span>
);

class DragonBase extends React.Component {
  render() {
    const options = {
    sizePerPage: 5,
    nextPageText: '>',
    prePageText: '<',
    hideSizePerPage: true,
    hidePageListOnlyOnePage: true,
    //showTotal: true,
    paginationTotalRenderer: customTotal
    }
    return (
      <BootstrapTable 
      keyField='id' data={ this.props.rows } columns={ this.props.headers }
      pagination={ paginationFactory(options) }
      defaultSorted={ defaultSorted }
      filter={ filterFactory() }
      cellEdit={ cellEditFactory({ mode: 'click' }) }
      selectRow={ selectRow }
      />
    );
  }
}


export default DragonBase;