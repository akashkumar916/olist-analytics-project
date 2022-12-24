// Chakra imports
import {
  Box,
  Button,
  Flex,
  SimpleGrid
} from "@chakra-ui/react";
// Custom components
import Card from "components/card/Card.js";
import React from "react";
import {
  lineChartDataTotalSpent,
  lineChartOptionsTotalSpent,
} from "variables/charts";
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import Chart from "react-apexcharts";
import Select from 'react-select'
import { format } from 'date-fns';

//export default function TotalSpent(props) {
class TotalSpent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      chartData: lineChartDataTotalSpent,
      chartOptions: lineChartOptionsTotalSpent,
      param1: "weekly", //set all params to default value
      param2: "",
      param3: "",
      param4: "",
      param5: "",
      param6: "",
      param7:"",
      dropdownData: []
    };
  //   axios.get("http://localhost:8080/getStates").then(response => {
  //     console.log("response", response)
  //     response.data.data.map(item => {
        
  //       this.state.dropdownData.push({ label: item.state, value: item.state });
      
  //     })
  
  // }, [])
 

  // const [handleChange] = useState(() => {

  //   return () => {

  //     setSelectedOption(selectedOption);
  //   };
  // });

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.handle = this.handle.bind(this);

  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  handle(e) {
    console.log(e.value);
    this.setState({ param2 :  e.value });
    console.log(this.state.param2);
  }

  onListChange(e) {
    console.log(e);
    //this.setState({ [e.target.name]: e.target.value });
    e.target.value.map(item => {
      this.state.param6.push(e.value);
    })
  }

  onSubmit(e) {
    console.log(this.state.param4)
    console.log(format(new Date(this.state.param4), 'dd-MMM-yy'))
    console.log(this.state.param5)
    console.log(format(new Date(this.state.param5), 'dd-MMM-yy'))
    console.log(this.state.param2)
    console.log(this.state.param1)
    console.log(this.state.param3)
  
    e.preventDefault();  // Here we prevent the default browser behavior
    const url = 'http://localhost:8080/productCategoryLogisticPerformance';
    const x = [];
    const y = [];
    axios.post(url,{"state":this.state.param2,"productCategory":this.state.param3, "startDate" : format(new Date(this.state.param4), 'dd-MMM-yy'), "endDate" : format(new Date(this.state.param5), 'dd-MMM-yy'), "frequency" : this.state.param1})
    .then(response => {
      console.log("response", response)
      response.data.data.map(item => {
        const d = format(new Date(item.weekstart), 'dd-MMM-yy')
       
        x.push(d);
        y.push(item.averageTimeTaken)
      })

      const newChartData = [
        {
          name: "averageTimeTaken",
          data: y
        }
      ]

      const newChartOptions = {
        chart: {
          toolbar: {
            show: false,
          },
          dropShadow: {
            enabled: true,
            top: 13,
            left: 0,
            blur: 10,
            opacity: 0.1,
            color: "#4318FF",
          },
        },
        colors: ["#4318FF", "#39B8FF"],
        markers: {
          size: 0,
          colors: "white",
          strokeColors: "#7551FF",
          strokeWidth: 3,
          strokeOpacity: 0.9,
          strokeDashArray: 0,
          fillOpacity: 1,
          discrete: [],
          shape: "circle",
          radius: 2,
          offsetX: 0,
          offsetY: 0,
          showNullDataPoints: true,
        },
        tooltip: {
          theme: "dark",
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: "smooth",
          type: "line",
        },
        xaxis: {
          type: "string",
          categories: x,
          labels: {
            style: {
              colors: "#A3AED0",
              fontSize: "12px",
              fontWeight: "500",
            },
          },
          axisBorder: {
            show: false,
          },
          axisTicks: {
            show: false,
          },
        },
        yaxis: {
          show: true,
        },
        legend: {
          show: false,
        },
        grid: {
          show: true,
          column: {
            color: ["#7551FF", "#39B8FF"],
            opacity: 0.5,
          },
        },
        color: ["#7551FF", "#39B8FF"],
      }

      this.setState({ chartData: newChartData });
      this.setState({ chartOptions: newChartOptions });

    }, [])
  }

  render() {
    return (
      <Card
        justifyContent='center'
        align='center'
        direction='column'
        w='100%'
        mb='0px'>
        <Form>
          <SimpleGrid columns={7} gap='10px' mb='10px'>
            <Form.Select name="param1" value={this.state.param1} onChange={this.onChange}>
              <option value="weekly"> Weekly </option>
              <option value="monthly"> Monthly </option>
            </Form.Select>
           
            <Form.Select name="param2" value={this.state.param2} onChange={this.onChange}>
            <option value="PE"></option>PE
            <option value="MT">MT</option>
            <option value="MS">MS</option>
            <option value="BA">BA</option>
            <option value="GO">GO</option>
            <option value="RR">RR</option>
            <option value="MG">MG</option>
            <option value="DF">DF</option>
            <option value="AM">AM</option>
            <option value="PR">PR</option>
            <option value="SC">SC</option>
            <option value="CEy">CE</option>
            <option value="AP">AP</option>
            <option value="RS">RS</option>
            <option value="RJ">RJ</option>
            <option value="RO">RO</option>
            <option value="PB">PB</option>
            <option value="PI">PI</option>
            <option value="SE">SE</option>
            <option value="AL">AL</option>
            <option value="PA">PA</option>
            <option value="AC">AC</option>
            <option value="SP">SP</option>
            <option value="ES">ES</option>
            <option value="TO">TO</option>
            <option value="RN">RN</option>
            <option value="MA">MA</option>
            
            </Form.Select>

            <Form.Select name="param3" value={this.state.param3} onChange={this.onChange}>
            <option value="brinquedos">brinquedos</option>
<option value="fashion_roupa_masculina">fashion_roupa_masculina</option>
<option value="beleza_saude">beleza_saude</option>
<option value="construcao_ferramentas_iluminacao">construcao_ferramentas_iluminacao</option>
<option value="livros_importados">livros_importados</option>
<option value="pc_gamer">pc_gamer</option>
<option value="cine_foto">cine_foto</option>
<option value="casa_conforto_2">casa_conforto_2</option>
<option value="portateis_cozinha_e_preparadores_de_alimentos">portateis_cozinha_e_preparadores_de_alimentos</option>
<option value="cama_mesa_banho">cama_mesa_banho</option>
<option value="relogios_presentes">relogios_presentes</option>
<option value="cool_stuff">cool_stuff</option>
<option value="eletrodomesticos">eletrodomesticos</option>
<option value="telefonia_fixa">telefonia_fixa</option>
<option value="instrumentos_musicais">instrumentos_musicais</option>
<option value="climatizacao">climatizacao</option>
<option value="pcs">pcs</option>
<option value="eletroportateis">eletroportateis</option>
<option value="dvds_blu_ray">dvds_blu_ray</option>
<option value="artigos_de_natal">artigos_de_natal</option>
<option value="eletronicos">eletronicos</option>
<option value="moveis_cozinha_area_de_servico_jantar_e_jardim">moveis_cozinha_area_de_servico_jantar_e_jardim</option>
<option value="market_place">market_place</option>
<option value="moveis_sala">moveis_sala</option>
<option value="alimentos">alimentos</option>
<option value="alimentos_bebidas">alimentos_bebidas</option>
<option value="construcao_ferramentas_jardim">construcao_ferramentas_jardim</option>
<option value="papelaria">papelaria</option>
<option value="fashion_bolsas_e_acessorios">fashion_bolsas_e_acessorios</option>
<option value="moveis_decoracao">moveis_decoracao</option>
<option value="moveis_escritorio">moveis_escritorio</option>
<option value="fashion_calcados">fashion_calcados</option>
<option value="fashion_underwear_e_moda_praia">fashion_underwear_e_moda_praia</option>
<option value="flores">flores</option>
<option value="livros_tecnicos">livros_tecnicos</option>
<option value="livros_interesse_geral">livros_interesse_geral</option>
<option value="bebidas">bebidas</option>
<option value="fashion_esporte">fashion_esporte</option>
<option value="fashion_roupa_feminina">fashion_roupa_feminina</option>
<option value="seguros_e_servicos">seguros_e_servicos</option>
<option value="utilidades_domesticas">utilidades_domesticas</option>
<option value="malas_acessorios">malas_acessorios</option>
<option value="esporte_lazer">esporte_lazer</option>
<option value="casa_construcao">casa_construcao</option>
<option value="consoles_games">consoles_games</option>
<option value="construcao_ferramentas_ferramentas">construcao_ferramentas_ferramentas</option>
<option value="musica">musica</option>
<option value="tablets_impressao_imagem">tablets_impressao_imagem</option>
<option value="audio">audio</option>
<option value="moveis_colchao_e_estofado">moveis_colchao_e_estofado</option>
<option value="cds_dvds_musicais">cds_dvds_musicais</option>
<option value="bebes">bebes</option>
<option value="perfumaria">perfumaria</option>
<option value="ferramentas_jardim">ferramentas_jardim</option>
<option value="industria_comercio_e_negocios">industria_comercio_e_negocios</option>
<option value="construcao_ferramentas_seguranca">construcao_ferramentas_seguranca</option>
<option value="construcao_ferramentas_construcao">construcao_ferramentas_construcao</option>
<option value="eletrodomesticos_2">eletrodomesticos_2</option>
<option value="la_cuisine">la_cuisine</option>
<option value="artes_e_artesanato">artes_e_artesanato</option>
<option value="fraldas_higiene">fraldas_higiene</option>
<option value="pet_shop">pet_shop</option>
<option value="informatica_acessorios">informatica_acessorios</option>
<option value="artes">artes</option>
<option value="automotivo">automotivo</option>
<option value="telefonia">telefonia</option>
<option value="artigos_de_festas">artigos_de_festas</option>
<option value="agro_industria_e_comercio">agro_industria_e_comercio</option>
<option value="sinalizacao_e_seguranca">sinalizacao_e_seguranca</option>
<option value="casa_conforto">casa_conforto</option>
<option value="portateis_casa_forno_e_cafe">portateis_casa_forno_e_cafe</option>
            </Form.Select>
            {/* //<Form.Select name="param3" value={this.state.param3} onChange={this.onChange}> */}
            {/* <Select name="param2" value={this.state.param2} onChange={this.handle} options={this.state.dropdownData} /> */}
           {/* // </Form.Select> */}
            {/* <Form.Select name="param7" value={this.state.param7} onChange={this.onChange}>
              <option value="SP"> SP </option>
          
            </Form.Select> */}
            <Form.Control type="date" name="param4" value={this.state.param4} onChange={this.onChange} />
            <Form.Control type="date" name="param5" value={this.state.param5} onChange={this.onChange} />
            {/* <Select options={this.state.dropdownData} /> */}
            <Button bg='black' color={"white"} type="submit" onClick={this.onSubmit}>Submit</Button>
          </SimpleGrid>
        </Form>
        <Flex w='100%' flexDirection={{ base: "column", lg: "row" }}>
          <Box minH='260px' minW='75%' mt='auto'>
            <Chart
              options={this.state.chartOptions}
              series={this.state.chartData}
              type='line'
              width='100%'
              height='100%'
            />
          </Box>
        </Flex>
      </Card>
    );
  }
}

export default TotalSpent;