package example.com.requests;

import example.com.models.BaseModel;

public interface CrudRepository<R> {
    public R post(BaseModel model );
    public R get();
    public R update();
    public R delete();
}
