package jh.tools;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

public interface BaseMapper<T> {
	
	@SelectProvider(type = SelectMethodProvider.class, method = "select")
    List<T> select(T entity);
	
}
