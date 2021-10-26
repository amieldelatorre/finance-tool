import { HomePage } from './components/home/HomePage';
import { Switch, Route } from 'react-router-dom';
import { Layout } from './components/common/Layout'


function App() {
  return (
    <Switch>
      <Layout>
        <Route exact path="/" component={HomePage} />
      </Layout>
    </Switch>
  );
}

export default App;
