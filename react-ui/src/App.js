import logo from './logo.svg';
import './App.css';
import {useState} from 'react';

function App() {
  const [data, setData] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [err, setErr] = useState('');
  const queryParams = new URLSearchParams(window.location.search);


  const getStreakUrl2 = () => {
    const myurl = queryParams.get('myurl');
    console.log('myurl', myurl);
    console.log('environment variables: ', process.env);

    var url = myurl;
    console.log('REACT_APP_STREAK_WEB_URL', process.env.REACT_APP_STREAK_WEB_URL)

    return url;
  }

  const getStreakUrl = () => {
    console.log('environment variables: ', process.env);

    var url = process.env.REACT_APP_STREAK_WEB_URL;
    console.log('url', url)

    return url;
  }

  const handleClick = async () => {
    setIsLoading(true);
    try {
      const response = await fetch(getStreakUrl());

      if (!response.ok) {
        throw new Error(`Error! status: ${response.status}`);
      }

      const result = await response.json();



      console.log('result is: ', JSON.stringify(result, null, 4));

      setData(result);
    } catch (err) {
      setErr(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClick2 = async () => {
    setIsLoading(true);
    try {
      const response = await fetch(getStreakUrl2());

      if (!response.ok) {
        throw new Error(`Error! status: ${response.status}`);
      }

      const result = await response.json();



      console.log('result is: ', JSON.stringify(result, null, 4));

      setData(result);
    } catch (err) {
      setErr(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  console.log(data);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <br/>
        <a
          className="App-link"
          href="http://localhost:8888/nonreactive"
          target="_blank"
          rel="noopener noreferrer"
        >
          nonreactive {getStreakUrl()}
        </a>
        <button onClick={handleClick}>Make streak-web request</button>
        <br/>
        <button onClick={handleClick2}>Make myurl param request</button>


      </header>
    </div>
  );
}

export default App;
