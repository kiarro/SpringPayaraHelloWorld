

function FalseWelcome(props) {
    return (
      <div className="App">
        <header className="App-header">
          <img src={kanna} className="App-logo" alt="dragon example" />
          <p >
            Добро пожаловать в самый крупный справочник о драконах
          </p>
          <a
            className="App-link"
            target="_blank"
            rel="noopener noreferrer"
            href='#section'
            onClick={props.onClick}
            
          >
            Узнать о драконах
          </a>
        </header>
      </div>
    );
  }