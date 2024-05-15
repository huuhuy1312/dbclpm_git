const Loader=({src})=>{
    return(
        <div className="loader_contains">
        <div>
            <img src={src}></img>
        </div>
      </div>
    )
}
export default Loader;