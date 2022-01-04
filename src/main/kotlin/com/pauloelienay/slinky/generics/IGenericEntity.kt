package com.pauloelienay.slinky.generics

import com.pauloelienay.slinky.extensions.isId
import java.lang.reflect.Field
import org.springframework.beans.BeanUtils

interface IGenericEntity<S> {
	var id: S?

	fun isUpdate(): Boolean {
		return id == null
	}

	fun idField(): Field {
		return this::class.java.declaredFields.toList()
			.parallelStream()
			.filter(Field::isId)
			.findFirst()
			.get()
	}

	fun <T> copyAllButId(target: T): T {
		BeanUtils.copyProperties(this, target as Any, "id")
		return target
	}
}