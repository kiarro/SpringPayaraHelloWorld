import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
// ...

const customTotal = (from, to, size) => (
    <span className="react-bootstrap-table-pagination-total">
        Showing {from} to {to} of {size} Results
    </span>
);

const columns = [{
    dataField: 'id',
    text: 'Product ID'
}, {
    dataField: 'name',
    text: 'Product Name'
}, {
    dataField: 'price',
    text: 'Product Price'
}];

const products = [
    { id: 1, name: 'item 1', price: '100' },
    { id: 2, name: 'item 2', price: '100' },
    { id: 3, name: 'item 3', price: '100' },
    { id: 4, name: 'item 4', price: '100' },
    { id: 5, name: 'item 5', price: '100' },
    { id: 6, name: 'item 6', price: '100' },
    { id: 7, name: 'item 7', price: '100' },
    { id: 8, name: 'item 8', price: '100' },
]

const options = {
    paginationSize: 4,
    pageStartIndex: 0,
    // alwaysShowAllBtns: true, // Always show next and previous button
    // withFirstAndLast: false, // Hide the going to First and Last page button
    // hideSizePerPage: true, // Hide the sizePerPage dropdown always
    // hidePageListOnlyOnePage: true, // Hide the pagination list when only one page
    firstPageText: 'First',
    prePageText: 'Back',
    nextPageText: 'Next',
    lastPageText: 'Last',
    nextPageTitle: 'First page',
    prePageTitle: 'Pre page',
    firstPageTitle: 'Next page',
    lastPageTitle: 'Last page',
    showTotal: true,
    paginationTotalRenderer: customTotal,
    disablePageTitle: true,
    sizePerPageList: [{
        text: '5', value: 5
    }, {
        text: '10', value: 10
    }, {
        text: 'All', value: products.length
    }] // A numeric array is also available. the purpose of above example is custom the text
};

function Test() {
    return (
        <BootstrapTable keyField='id' data={products} columns={columns} pagination={paginationFactory(options)} />
    );
}

export default Test;