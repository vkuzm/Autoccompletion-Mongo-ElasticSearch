import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import './App.css';

const App = () => {
  const [search, setSearch] = useState('');
  const [suggestProduct, setSuggestProduct] = useState([]);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    if (search.length >= 2) {
      fetch(`http://localhost:8080/api/v1/products/search?q=${search}`)
        .then((res) => res.json())
        .then((products) => {
          setSuggestProduct(products);
        })
        .catch((error) => console.log(error));
    } else {
      setSuggestProduct([]);
    }
  }, [search]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/v1/products`)
      .then((res) => res.json())
      .then((products) => {
        setProducts(products.content);
      })
      .catch((error) => console.log(error));
  }, []);

  return (
    <div className="App">
      <section>
        <Container>
          <div className="search">
            <h2>Search products</h2>
            <div className="input">
              <input type="text" onChange={(event) => setSearch(event.target.value)} />
            </div>
            {
              suggestProduct.length > 0 && <div className="search-result">
                {
                  suggestProduct.map((product) => (
                    <div className="item" key={product.name}>
                      <div className="image"><a href={product.url}><img src={product.image} alt={product.name} /></a></div>
                      <div className="name"><a href={product.url}>{product.name}</a></div>
                    </div>
                  ))
                }
              </div>
            }
          </div>

          <div className="products">
            <h2>Products</h2>
            <Row xs={2} md={4} className="g-4">
              {products.map((product) => (
                <Col key={product.name}>
                  <Card>
                    <Card.Img variant="top" src={product.image} alt={product.name} />
                    <Card.Body>
                      <Card.Title>{product.name}</Card.Title>
                      <Card.Text>{product.description}</Card.Text>
                      <Button variant="primary" href={product.url}>View product</Button>
                    </Card.Body>
                  </Card>
                </Col>
              ))}
            </Row>
          </div>

        </Container>
      </section>

    </div>
  );
}

export default App;
